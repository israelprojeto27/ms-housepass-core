package com.housepass.imoveis.app.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.imoveis.app.dtos.AvaliacaoDTO;
import com.housepass.imoveis.app.dtos.ComentarioDTO;
import com.housepass.imoveis.app.dtos.CreateAvaliacaoDTO;
import com.housepass.imoveis.app.dtos.CreateNotificationDTO;
import com.housepass.imoveis.app.dtos.VisitanteDTO;
import com.housepass.imoveis.app.entities.Avaliacao;
import com.housepass.imoveis.app.entities.Comentario;
import com.housepass.imoveis.app.entities.Imovel;
import com.housepass.imoveis.app.entities.UserResume;
import com.housepass.imoveis.app.enums.NotificationType;
import com.housepass.imoveis.app.exceptions.DataNotFoundException;
import com.housepass.imoveis.app.feignclients.NotificationClient;
import com.housepass.imoveis.app.repositories.AvaliacaoRepository;
import com.housepass.imoveis.app.repositories.ImovelRepository;
import com.housepass.imoveis.app.repositories.UserResumeRepository;

@Service
public class AvaliacaoService {
	
	@Autowired
	private AvaliacaoRepository repository;
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	@Autowired
	private UserResumeRepository userResumeRepository;
	
	@Autowired
	private NotificationClient notificationClient;
	

	@Transactional
	public ResponseEntity<?> create(CreateAvaliacaoDTO dto) {
		
		Imovel imovel = imovelRepository.findById(dto.getImovelId()).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		UserResume userResume = userResumeRepository.findByUserId(dto.getUserId());
	
		Avaliacao avaliacao = CreateAvaliacaoDTO.toEntity(dto);			
		avaliacao.setUserAvaliacao(userResume);
		repository.insert(avaliacao);
		
		imovel.getAvaliacoes().add(avaliacao);		
		imovelRepository.save(imovel);
		
		CreateNotificationDTO createNotificationDTO = new CreateNotificationDTO();
		createNotificationDTO.setDescription("Um nova avaliação foi feito sobre o seu imóvel: " + imovel.getTitulo());
		createNotificationDTO.setType(NotificationType.EVALUATION_IMOVEL);
		createNotificationDTO.setUserId(imovel.getUserOwner().getUserId());
		createNotificationDTO.setUserSendId(userResume.getUserId());
		createNotificationDTO.setImovelId(imovel.getId());
		notificationClient.addNotification(createNotificationDTO);
		
		return new ResponseEntity<>("Avaliação foi adicionada com sucesso", HttpStatus.CREATED);
	}



	public ResponseEntity<?> findAll() {		
		return new ResponseEntity<>(repository.findAll().stream()
														.map(AvaliacaoDTO::fromEntity)
														.collect(Collectors.toList()), 
									HttpStatus.OK);
	}



	public ResponseEntity<?> findById(String avaliacaoId) {
		Avaliacao avaliacao = repository.findById(avaliacaoId).orElseThrow(() -> new DataNotFoundException("Avaliação não encontrada"));
		return new ResponseEntity<>(AvaliacaoDTO.fromEntity(avaliacao), HttpStatus.OK);
	}


	@Transactional
	public ResponseEntity<?> delete(String avaliacaoId) {
		Avaliacao avaliacao = repository.findById(avaliacaoId).orElseThrow(() -> new DataNotFoundException("Avaliação não encontrada"));
		Imovel imovel = imovelRepository.findById(avaliacao.getImovelId()).orElseThrow(() -> new DataNotFoundException("Imóvel não encontrado"));
		
		imovel.getAvaliacoes().remove(avaliacao);
		imovelRepository.save(imovel);
		
		repository.delete(avaliacao);		
		return new ResponseEntity<>("Avaliação foi removida com sucesso", HttpStatus.NO_CONTENT);
	}



	public ResponseEntity<?> findByFilter(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("createdDate").descending());	
		
		return new ResponseEntity<>(repository.findAll(pageable).stream()
														.map(AvaliacaoDTO::fromEntity)
														.collect(Collectors.toList()), 
										HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> findByImovelId(String imovelId) {
		Imovel imovel = imovelRepository.findById(imovelId).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		
		return new ResponseEntity<>(imovel.getAvaliacoes().stream()
													.map(AvaliacaoDTO::fromEntity)
													.collect(Collectors.toList()), 
									HttpStatus.OK);
	}


	public ResponseEntity<?> findByFilterByImovelId(String imovelId, int page, int size) {
		Imovel imovel = imovelRepository.findById(imovelId).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		
		return new ResponseEntity<>(imovel.getAvaliacoes().stream()
													.skip(page * size)
													.limit(size)
													.map(AvaliacaoDTO::fromEntity)
													.collect(Collectors.toList()), 
									HttpStatus.OK);
	}

}
