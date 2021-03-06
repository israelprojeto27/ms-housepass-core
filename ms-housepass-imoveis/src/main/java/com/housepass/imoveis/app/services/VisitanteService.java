package com.housepass.imoveis.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.imoveis.app.dtos.ComentarioDTO;
import com.housepass.imoveis.app.dtos.CreateNotificationDTO;
import com.housepass.imoveis.app.dtos.CreateVisitanteDTO;
import com.housepass.imoveis.app.dtos.UpdateQuantImovelUserDTO;
import com.housepass.imoveis.app.dtos.VisitanteDTO;
import com.housepass.imoveis.app.entities.Comentario;
import com.housepass.imoveis.app.entities.Imovel;
import com.housepass.imoveis.app.entities.UserResume;
import com.housepass.imoveis.app.entities.Visitante;
import com.housepass.imoveis.app.enums.NotificationType;
import com.housepass.imoveis.app.enums.TypeQuantImovel;
import com.housepass.imoveis.app.exceptions.DataNotFoundException;
import com.housepass.imoveis.app.feignclients.NotificationClient;
import com.housepass.imoveis.app.feignclients.UserClient;
import com.housepass.imoveis.app.repositories.ImovelRepository;
import com.housepass.imoveis.app.repositories.UserResumeRepository;
import com.housepass.imoveis.app.repositories.VisitanteRepository;

@Service
public class VisitanteService {

	
	@Autowired
	private VisitanteRepository repository;
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	@Autowired
	private UserResumeRepository userResumeRepository;
	
	@Autowired
	private NotificationClient notificationClient;
	
	@Autowired
	private UserClient userClient;
	
		
	@Transactional
	public ResponseEntity<?> create(CreateVisitanteDTO dto) {
		
		Imovel imovel = imovelRepository.findById(dto.getImovelId()).orElseThrow(() -> new DataNotFoundException("Imovel n??o encontrado"));
		UserResume userResume = userResumeRepository.findByUserId(dto.getUserId());
		
		Visitante visitante = CreateVisitanteDTO.toEntity(dto);
		visitante.setUserVisitante(userResume);		
		repository.insert(visitante);
		
		imovel.getVisitantes().add(visitante);
		imovelRepository.save(imovel);
		
		CreateNotificationDTO createNotificationDTO = new CreateNotificationDTO();
		createNotificationDTO.setDescription("Um dos seus im??veis recebeu uma nova visita: " + imovel.getTitulo());
		createNotificationDTO.setType(NotificationType.VISITOR);
		createNotificationDTO.setUserId(imovel.getUserOwner().getUserId());
		createNotificationDTO.setUserSendId(userResume.getUserId());
		createNotificationDTO.setImovelId(imovel.getId());
		notificationClient.addNotification(createNotificationDTO);
		
		UpdateQuantImovelUserDTO updateQuantImovelUserDTO = new UpdateQuantImovelUserDTO(TypeQuantImovel.VISITOR);
		userClient.updateQuantImovel(imovel.getUserOwner().getUserId(), imovel.getId(), updateQuantImovelUserDTO);
		
		return new ResponseEntity<>("Visitante foi adicionada com sucesso", HttpStatus.CREATED);
	}


	public ResponseEntity<?> findAll() {		
		return new ResponseEntity<>(repository.findAll().stream()
														.map(VisitanteDTO::fromEntity)
														.collect(Collectors.toList()), 
									HttpStatus.OK);
	}


	public ResponseEntity<?> findById(String visitanteId) {
		Visitante visitante = repository.findById(visitanteId).orElseThrow(() -> new DataNotFoundException("Visitante n??o encontrado"));
		return new ResponseEntity<>(VisitanteDTO.fromEntity(visitante), HttpStatus.OK);
	}


	@Transactional
	public ResponseEntity<?> delete(String visitanteId) {
		Visitante visitante = repository.findById(visitanteId).orElseThrow(() -> new DataNotFoundException("Visitante n??o encontrado"));
		Imovel imovel = imovelRepository.findById(visitante.getImovelId()).orElseThrow(() -> new DataNotFoundException("Im??vel n??o encontrado"));
		
		imovel.getVisitantes().remove(visitante);
		imovelRepository.save(imovel);
		
		repository.delete(visitante);		
		return new ResponseEntity<>("Visitante foi removido com sucesso", HttpStatus.NO_CONTENT);
	}


	public ResponseEntity<?> findByFilter(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("createdDate").descending());		
		
		return new ResponseEntity<>(repository.findAll(pageable).stream()
									.map(VisitanteDTO::fromEntity)
									.collect(Collectors.toList()), 
					HttpStatus.OK);
	}


	public ResponseEntity<?> findByImovelId(String imovelId) {
		Imovel imovel = imovelRepository.findById(imovelId).orElseThrow(() -> new DataNotFoundException("Imovel n??o encontrado"));
		
		return new ResponseEntity<>(imovel.getVisitantes().stream()
													.map(VisitanteDTO::fromEntity)
													.collect(Collectors.toList()), 
									HttpStatus.OK);
	}


	public ResponseEntity<?> findByFilterByImovelId(String imovelId, int page, int size) {
		Imovel imovel = imovelRepository.findById(imovelId).orElseThrow(() -> new DataNotFoundException("Imovel n??o encontrado"));
		
		return new ResponseEntity<>(imovel.getVisitantes().stream()
													.skip(page * size)
													.limit(size)
													.map(VisitanteDTO::fromEntity)
													.collect(Collectors.toList()), 
									HttpStatus.OK);
	}

}
