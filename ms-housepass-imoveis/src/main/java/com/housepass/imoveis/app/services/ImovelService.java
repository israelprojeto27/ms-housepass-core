package com.housepass.imoveis.app.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.imoveis.app.dtos.CreateImovelDTO;
import com.housepass.imoveis.app.dtos.ImovelDTO;
import com.housepass.imoveis.app.dtos.UpdateImovelDTO;
import com.housepass.imoveis.app.dtos.UserOwnerDTO;
import com.housepass.imoveis.app.entities.Imovel;
import com.housepass.imoveis.app.entities.User;
import com.housepass.imoveis.app.repositories.ImovelRepository;
import com.housepass.imoveis.app.feignclients.UserClient;


@Service
public class ImovelService {
	
	@Autowired
	ImovelRepository repository;
	 
	@Autowired
	UserClient userClient;

	@Transactional
	public void create(CreateImovelDTO dto) {
		Imovel imovel = CreateImovelDTO.toEntity(dto);
		UserOwnerDTO userOwnerDTO = userClient.findByIdOwner(dto.getUserId()).getBody();
		User user = UserOwnerDTO.toEntity(userOwnerDTO);
		imovel.setUserOwner(user);
		repository.insert(imovel);
	}

	public List<ImovelDTO> findAll() {
		List<Imovel> list = repository.findAll();
		return list.stream()
				.map(imovel -> ImovelDTO.fromEntity(imovel))
				.collect(Collectors.toList());
	}

	public ImovelDTO findById(String imovelId) {
		Optional<Imovel> imoveleOpt = repository.findById(imovelId);
		return ImovelDTO.fromEntity(imoveleOpt.get());
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

}
