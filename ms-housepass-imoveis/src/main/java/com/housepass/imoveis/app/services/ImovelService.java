package com.housepass.imoveis.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.imoveis.app.dtos.ActionLikeUnLikeDTO;
import com.housepass.imoveis.app.dtos.CreateImovelDTO;
import com.housepass.imoveis.app.dtos.ImovelDTO;
import com.housepass.imoveis.app.dtos.ImovelUserDTO;
import com.housepass.imoveis.app.dtos.UpdateImovelDTO;
import com.housepass.imoveis.app.dtos.UserOwnerDTO;
import com.housepass.imoveis.app.entities.Imovel;
import com.housepass.imoveis.app.entities.LikeImovel;
import com.housepass.imoveis.app.entities.User;
import com.housepass.imoveis.app.entities.UserResume;
import com.housepass.imoveis.app.enums.TypeActionLike;
import com.housepass.imoveis.app.exceptions.DataNotFoundException;
import com.housepass.imoveis.app.feignclients.UserClient;
import com.housepass.imoveis.app.repositories.ImovelCustomRepository;
import com.housepass.imoveis.app.repositories.ImovelRepository;
import com.housepass.imoveis.app.repositories.LikeImovelRepository;
import com.housepass.imoveis.app.repositories.UserResumeRepository;



@Service
public class ImovelService {
	
	@Autowired
	ImovelRepository repository;
	 
	@Autowired
	UserClient userClient;
	
	@Autowired
	UserResumeRepository userResumeRepository;
	
	@Autowired
	LikeImovelRepository likeImovelRepository;

	@Autowired
	ImovelCustomRepository imovelCustomRepository;

	
	@Transactional
	public ResponseEntity<?> create(CreateImovelDTO dto) {
		Imovel imovel = CreateImovelDTO.toEntity(dto);
		UserOwnerDTO userOwnerDTO = userClient.findByIdOwner(dto.getUserId()).getBody();
		User user = UserOwnerDTO.toEntity(userOwnerDTO);
		imovel.setUserOwner(user);
		repository.insert(imovel);
		
		ImovelUserDTO imovelUserDTO = ImovelUserDTO.fromEntity(imovel);
		userClient.addImovelUser(user.getUserId(), imovelUserDTO);
		
		String ret = userClient.findImovelByImovelIdByUserId(user.getUserId(), imovel.getId());
		if ( ret == null || (ret != null && ret.equals("N"))) {
			repository.delete(imovel);
			return new ResponseEntity<>("Não foi possível finalizar o cadastro do imovel", HttpStatus.NOT_ACCEPTABLE);
		}
		
		return new ResponseEntity<>("Imovel criado com sucesso", HttpStatus.CREATED);
	}

	public ResponseEntity<?> findAll() {		
		return new ResponseEntity<>(repository.findAll().stream()
														.map(ImovelDTO::fromEntity)
														.collect(Collectors.toList()), 
									HttpStatus.OK);
	}

	public ResponseEntity<?> findById(String imovelId) {
		Imovel imovel = repository.findById(imovelId).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		return new ResponseEntity<>(ImovelDTO.fromEntity(imovel), HttpStatus.OK);
	}
	
	public ResponseEntity<?> update(String imovelId, UpdateImovelDTO dto) {
		Imovel imovel = repository.findById(imovelId).get();
		
		imovel.setTitulo(dto.getTitulo());
		imovel.setImageUrl(dto.getImoveImageUrl());
		imovel.setLocalizacao(dto.getLocalizacao());
		imovel.setQuantComments(dto.getQuantComments());
		imovel.setQuantLikes(dto.getQuantLikes());
		imovel.setQuantShares(dto.getQuantShares());
		imovel.setQuantViews(dto.getQuantViews());		
		
		repository.save(imovel);
		
		userClient.updateImovelUser(imovel.getUserOwner().getUserId(), imovel.getId(), dto);
		
		return new ResponseEntity<>("Imovel atualizado com sucesso", HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<?> findByFilter(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("updatedAt").descending());
		
		return new ResponseEntity<>(repository.findAll(pageable).stream()
														.map(ImovelDTO::fromEntity)
														.collect(Collectors.toList()), 
										HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<?> actionLikeUnlikeImovelId(String imovelId, ActionLikeUnLikeDTO dto) {
		Imovel imovel = repository.findById(imovelId).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		UserResume userResume = userResumeRepository.findByUserId(dto.getUserId());	
		
		
		if ( dto.getAction().equals(TypeActionLike.LIKE)) {
			LikeImovel likeImovel = new LikeImovel(dto.getUserId(), imovel.getId(), userResume);	
			likeImovelRepository.insert(likeImovel);
			
			imovel.getLikesImovel().add(likeImovel);
			imovel.setQuantLikes(imovel.getQuantLikes() + 1);
		}
		else if ( dto.getAction().equals(TypeActionLike.UNLIKE)) {
			LikeImovel likeImovel = likeImovelRepository.findByUserIdAndImovelId(dto.getUserId(), imovel.getId());			
			imovel.getLikesImovel().remove(likeImovel);
			imovel.setQuantLikes(imovel.getQuantLikes() - 1);
			likeImovelRepository.delete(likeImovel);
		}
		repository.save(imovel);
		
		
		return new ResponseEntity<>("Action " + dto + " adicionado com sucesso ao imovel", HttpStatus.OK);
	}	
	
	public ResponseEntity<?> searchImoveisCustom(String action, String type, String priceMin, String priceMax,  
											     String quantQuartos,	String  quantBanheiros, String quantGaragem,
											     String quantSuite, String areaMin, String areaMax,
											     int page, int size) {
		
	List<Imovel> imoveis = imovelCustomRepository.findAllCustom( action, type, priceMin, priceMax,
																 quantQuartos, quantBanheiros, quantGaragem, quantSuite, areaMin, areaMax, 
																 page, size);
		return new ResponseEntity<>(imoveis.stream()
											.map(ImovelDTO::fromEntity)
											.collect(Collectors.toList()), 
		HttpStatus.OK);
		
	}
	
	

}
