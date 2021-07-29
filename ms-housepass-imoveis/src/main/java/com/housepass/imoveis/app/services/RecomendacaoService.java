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
import com.housepass.imoveis.app.dtos.CreateNotificationDTO;
import com.housepass.imoveis.app.dtos.CreateRecomendacaoDTO;
import com.housepass.imoveis.app.dtos.RecomendacaoDTO;
import com.housepass.imoveis.app.dtos.UpdateQuantImovelUserDTO;
import com.housepass.imoveis.app.dtos.VisitanteDTO;
import com.housepass.imoveis.app.entities.Comentario;
import com.housepass.imoveis.app.entities.Imovel;
import com.housepass.imoveis.app.entities.Recomendacao;
import com.housepass.imoveis.app.entities.UserResume;
import com.housepass.imoveis.app.enums.NotificationType;
import com.housepass.imoveis.app.enums.TypeQuantImovel;
import com.housepass.imoveis.app.exceptions.DataNotFoundException;
import com.housepass.imoveis.app.feignclients.NotificationClient;
import com.housepass.imoveis.app.feignclients.UserClient;
import com.housepass.imoveis.app.repositories.ImovelRepository;
import com.housepass.imoveis.app.repositories.RecomendacaoRepository;
import com.housepass.imoveis.app.repositories.UserResumeRepository;

@Service
public class RecomendacaoService {

	@Autowired
	private RecomendacaoRepository repository;
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	@Autowired
	private UserResumeRepository userResumeRepository;
	
	
	@Autowired
	private NotificationClient notificationClient;	
	
	@Autowired
	private UserClient userClient;
	
	
	@Transactional
	public ResponseEntity<?> create(CreateRecomendacaoDTO dto) {

		Imovel imovel = imovelRepository.findById(dto.getImovelId()).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		UserResume userResume = userResumeRepository.findByUserId(dto.getUserId());
		
		Recomendacao recomendacao = CreateRecomendacaoDTO.toEntity(dto);
		recomendacao.setUserRecomendacao(userResume);
		repository.insert(recomendacao);		
		
		imovel.getRecomendacoes().add(recomendacao);
		imovelRepository.save(imovel);
		
		CreateNotificationDTO createNotificationDTO = new CreateNotificationDTO();
		createNotificationDTO.setDescription("Uma nova recomendação foi feita sobre o seu imóvel: " + imovel.getTitulo());
		createNotificationDTO.setType(NotificationType.RECOMMENDATION_IMOVEL);
		createNotificationDTO.setUserId(imovel.getUserOwner().getUserId());
		createNotificationDTO.setUserSendId(userResume.getUserId());
		createNotificationDTO.setImovelId(imovel.getId());
		notificationClient.addNotification(createNotificationDTO);
		
		UpdateQuantImovelUserDTO updateQuantImovelUserDTO = new UpdateQuantImovelUserDTO(TypeQuantImovel.RECOMMENDATION);
		userClient.updateQuantImovel(imovel.getUserOwner().getUserId(), imovel.getId(), updateQuantImovelUserDTO);
		
		return new ResponseEntity<>("Recomendação foi adicionada com sucesso", HttpStatus.CREATED);
	}



	public ResponseEntity<?> findAll() {		
		return new ResponseEntity<>(repository.findAll().stream()
														.map(RecomendacaoDTO::fromEntity)
														.collect(Collectors.toList()), 
									HttpStatus.OK);
	}



	public ResponseEntity<?> findById(String recomendacaoId) {
		Recomendacao recomendacao = repository.findById(recomendacaoId).orElseThrow(() -> new DataNotFoundException("Recomendacao não encontrada"));
		return new ResponseEntity<>(RecomendacaoDTO.fromEntity(recomendacao), HttpStatus.OK);
	}


	@Transactional
	public ResponseEntity<?> delete(String recomendacaoId) {
		Recomendacao recomendacao = repository.findById(recomendacaoId).orElseThrow(() -> new DataNotFoundException("Recomendacao não encontrada"));
		Imovel imovel = imovelRepository.findById(recomendacao.getImovelId()).orElseThrow(() -> new DataNotFoundException("Imóvel não encontrado"));
		
		imovel.getRecomendacoes().remove(recomendacao);
		imovelRepository.save(imovel);		
		
		repository.delete(recomendacao);		
		return new ResponseEntity<>("Recomendação foi removida com sucesso", HttpStatus.NO_CONTENT);
	}



	public ResponseEntity<?> findByFilter(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("createdDate").descending());		
		
		return new ResponseEntity<>(repository.findAll(pageable).stream()
																.map(RecomendacaoDTO::fromEntity)
																.collect(Collectors.toList()), 
					HttpStatus.OK);
	}



	public ResponseEntity<?> findByImovelId(String imovelId) {
		Imovel imovel = imovelRepository.findById(imovelId).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		
		return new ResponseEntity<>(imovel.getRecomendacoes().stream()
													.map(RecomendacaoDTO::fromEntity)
													.collect(Collectors.toList()), 
									HttpStatus.OK);
	}



	public ResponseEntity<?> findByFilterByImovelId(String imovelId, int page, int size) {
		Imovel imovel = imovelRepository.findById(imovelId).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		
		return new ResponseEntity<>(imovel.getRecomendacoes().stream()
													.skip(page * size)
													.limit(size)
													.map(RecomendacaoDTO::fromEntity)
													.collect(Collectors.toList()), 
									HttpStatus.OK);
	}


}
