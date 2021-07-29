package com.housepass.imoveis.app.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.imoveis.app.dtos.ComentarioDTO;
import com.housepass.imoveis.app.dtos.CreateComentarioDTO;
import com.housepass.imoveis.app.dtos.CreateNotificationDTO;
import com.housepass.imoveis.app.dtos.UpdateQuantImovelUserDTO;
import com.housepass.imoveis.app.dtos.VisitanteDTO;
import com.housepass.imoveis.app.entities.Comentario;
import com.housepass.imoveis.app.entities.Imovel;
import com.housepass.imoveis.app.entities.UserResume;
import com.housepass.imoveis.app.enums.NotificationType;
import com.housepass.imoveis.app.enums.TypeQuantImovel;
import com.housepass.imoveis.app.exceptions.DataNotFoundException;
import com.housepass.imoveis.app.feignclients.NotificationClient;
import com.housepass.imoveis.app.feignclients.UserClient;
import com.housepass.imoveis.app.repositories.ComentarioRepository;
import com.housepass.imoveis.app.repositories.ImovelRepository;
import com.housepass.imoveis.app.repositories.UserResumeRepository;


@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository repository;
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	@Autowired
	private UserResumeRepository userResumeRepository;
	
	@Autowired
	private NotificationClient notificationClient;
	
	@Autowired
	private UserClient userClient;
	
	@Transactional
	public ResponseEntity<?> create(CreateComentarioDTO dto) {

		Imovel imovel = imovelRepository.findById(dto.getImovelId()).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		UserResume userResume = userResumeRepository.findByUserId(dto.getUserId());
		
		Comentario comentario = CreateComentarioDTO.toEntity(dto);
		comentario.setUserComentario(userResume);		
		repository.insert(comentario);
		
		imovel.getComentarios().add(comentario);
		imovel.setQuantComments(imovel.getQuantComments() + 1);
		imovelRepository.save(imovel);
		
		CreateNotificationDTO createNotificationDTO = new CreateNotificationDTO();
		createNotificationDTO.setDescription("Um novo comentário sobre o seu imóvel: " + imovel.getTitulo());
		createNotificationDTO.setType(NotificationType.COMMENTS);
		createNotificationDTO.setUserId(imovel.getUserOwner().getUserId());
		createNotificationDTO.setUserSendId(userResume.getUserId());
		createNotificationDTO.setImovelId(imovel.getId());
		notificationClient.addNotification(createNotificationDTO);
		
		UpdateQuantImovelUserDTO updateQuantImovelUserDTO = new UpdateQuantImovelUserDTO(TypeQuantImovel.COMMENT);
		userClient.updateQuantImovel(imovel.getUserOwner().getUserId(), imovel.getId(), updateQuantImovelUserDTO);
		
		return new ResponseEntity<>("Comentario foi adicionada com sucesso", HttpStatus.CREATED);
	}



	public ResponseEntity<?> findAll() {		
		return new ResponseEntity<>(repository.findAll().stream()
														.map(ComentarioDTO::fromEntity)
														.collect(Collectors.toList()), 
									HttpStatus.OK);
	}



	public ResponseEntity<?> findById(String comentarioId) {
		Comentario comentario = repository.findById(comentarioId).orElseThrow(() -> new DataNotFoundException("Comentário não encontrado"));
		return new ResponseEntity<>(ComentarioDTO.fromEntity(comentario), HttpStatus.OK);
	}


	@Transactional
	public ResponseEntity<?> delete(String comentarioId) {
		Comentario comentario = repository.findById(comentarioId).orElseThrow(() -> new DataNotFoundException("Comentário não encontrado"));
		Imovel imovel = imovelRepository.findById(comentario.getImovelId()).orElseThrow(() -> new DataNotFoundException("Imóvel não encontrado"));
		
		imovel.getComentarios().remove(comentario);
		imovelRepository.save(imovel);
		
		repository.delete(comentario);		
		return new ResponseEntity<>("Comentario foi removido com sucesso", HttpStatus.NO_CONTENT);
	}



	public ResponseEntity<?> findByFilter(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("createdDate").descending());		
		return new ResponseEntity<>(repository.findAll(pageable).stream()
													.map(ComentarioDTO::fromEntity)
													.collect(Collectors.toList()), 
									HttpStatus.OK);
	}



	public ResponseEntity<?> findByImovelId(String imovelId) {
		Imovel imovel = imovelRepository.findById(imovelId).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		
		return new ResponseEntity<>(imovel.getComentarios().stream()
													.map(ComentarioDTO::fromEntity)
													.collect(Collectors.toList()), 
									HttpStatus.OK);
	}


	public ResponseEntity<?> findByFilterByImovelId(String imovelId, int page, int size) {
		Imovel imovel = imovelRepository.findById(imovelId).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		
		return new ResponseEntity<>(imovel.getComentarios().stream()
													.skip(page * size)
													.limit(size)
													.map(ComentarioDTO::fromEntity)
													.collect(Collectors.toList()), 
									HttpStatus.OK);
	}
	
	

}
