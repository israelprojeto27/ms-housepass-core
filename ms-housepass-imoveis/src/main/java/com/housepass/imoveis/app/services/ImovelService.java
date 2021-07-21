package com.housepass.imoveis.app.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.imoveis.app.dtos.CreateImovelDTO;
import com.housepass.imoveis.app.dtos.ImovelDTO;
import com.housepass.imoveis.app.dtos.ImovelUserDTO;
import com.housepass.imoveis.app.dtos.UpdateImovelDTO;
import com.housepass.imoveis.app.dtos.UserOwnerDTO;
import com.housepass.imoveis.app.entities.Imovel;
import com.housepass.imoveis.app.entities.User;
import com.housepass.imoveis.app.exceptions.DataNotFoundException;
import com.housepass.imoveis.app.feignclients.UserClient;
import com.housepass.imoveis.app.repositories.ImovelRepository;


@Service
public class ImovelService {
	
	@Autowired
	ImovelRepository repository;
	 
	@Autowired
	UserClient userClient;
	
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


}
