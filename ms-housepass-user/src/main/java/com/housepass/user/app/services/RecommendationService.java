package com.housepass.user.app.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.user.app.dtos.ConquerDTO;
import com.housepass.user.app.dtos.CreateNotificationDTO;
import com.housepass.user.app.dtos.CreateRecomendationUserDTO;
import com.housepass.user.app.dtos.RecommendationDTO;
import com.housepass.user.app.dtos.UpdateStatusRecomendationUserDTO;
import com.housepass.user.app.entities.Recommendation;
import com.housepass.user.app.entities.User;
import com.housepass.user.app.entities.UserResume;
import com.housepass.user.app.enums.NotificationType;
import com.housepass.user.app.enums.StatusEnum;
import com.housepass.user.app.exceptions.DataNotFoundException;
import com.housepass.user.app.feignClient.NotificationClient;
import com.housepass.user.app.repositories.RecommendationRepository;
import com.housepass.user.app.repositories.UserRepository;
import com.housepass.user.app.repositories.UserResumeRepository;

@Service
public class RecommendationService {
	
	@Autowired
	private RecommendationRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserResumeRepository userResumeRepository;
	
	@Autowired
	private NotificationClient notificationClient;

	
	@Transactional
	public ResponseEntity<?> create(CreateRecomendationUserDTO dto) {

		UserResume userResumeSendRecommendation = userResumeRepository.findByUserId(dto.getUserSendRecommendationId());
		User userReceiveRecommendation = userRepository.findById(dto.getUserReceiveRecommendationId()).orElseThrow(() -> new DataNotFoundException("Usu??rio n??o encontrada"));
		
		Recommendation recommendation = CreateRecomendationUserDTO.toEntity(dto);
		recommendation.setUserSendRecommendation(userResumeSendRecommendation);
		repository.insert(recommendation);
		
		if ( userReceiveRecommendation.getRecommendations() != null ) {
			userReceiveRecommendation.getRecommendations().add(recommendation);
		}
		else {
			userReceiveRecommendation.setRecommendations(Arrays.asList(recommendation));
		}
		userRepository.save(userReceiveRecommendation);
		
		CreateNotificationDTO createNotificationDTO = new CreateNotificationDTO();
		createNotificationDTO.setDescription("Uma nova recomenda????o foi enviada pelo usuario: " + userResumeSendRecommendation.getUserName());
		createNotificationDTO.setType(NotificationType.RECOMMENDATION_USER);
		createNotificationDTO.setUserId(userReceiveRecommendation.getId());
		createNotificationDTO.setUserSendId(userResumeSendRecommendation.getUserId());
		notificationClient.addNotification(createNotificationDTO);
		
		return new ResponseEntity<>("Recomenda????o enviada para o usu??rio foi enviada com sucesso", HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(repository.findAll().stream()
													    .map(RecommendationDTO::fromEntity)
													    .collect(Collectors.toList()),
									HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<?> updateStatus(UpdateStatusRecomendationUserDTO dto) {
		Recommendation recommendation = repository.findById(dto.getRecommendationId()).orElseThrow(() -> new DataNotFoundException("Recomenda????o n??o encontrada"));
		
		if (! recommendation.getUserReceiveRecommendationId().equals(dto.getUserReceiveRecommendationId())) {			
			return new ResponseEntity<>("N??o ?? poss??vel atualizar status da Recomenda????o, pois usuario nao tem autoriza????o para alterar", HttpStatus.BAD_REQUEST);
		}
		
		if ( dto.getStatusRecommendation().equals(StatusEnum.REJECTED)) {
			User user = userRepository.findById(recommendation.getUserReceiveRecommendationId()).orElseThrow(() -> new DataNotFoundException("Usu??rio n??o encontrado"));
			
			user.getRecommendations().remove(recommendation);
			userRepository.save(user);
			
			repository.delete(recommendation);
		}
		else {
			recommendation.setStatusEvaluation(dto.getStatusRecommendation());
			recommendation.setUpdatedDate(LocalDateTime.now());
			repository.save(recommendation);	
		}
		
		return new ResponseEntity<>("Recomenda????o atualizada com sucesso", HttpStatus.NO_CONTENT);
	}
	
	
	@Transactional
	public ResponseEntity<?> deleteById(String recommendationId) {
		
		Recommendation recommendation = repository.findById(recommendationId).orElseThrow(() -> new DataNotFoundException("Recomenda????o n??o encontrada"));
		User user = userRepository.findById(recommendation.getUserReceiveRecommendationId()).orElseThrow(() -> new DataNotFoundException("Usu??rio n??o encontrado"));
		
		user.getRecommendations().remove(recommendation);
		userRepository.save(user);		
		repository.delete(recommendation);
		
		return new ResponseEntity<>("Recomenda????o removida com sucesso", HttpStatus.NO_CONTENT);
	}


	public ResponseEntity<?> findByFilter(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("createdDate").descending());
		return new ResponseEntity<>(repository.findAll(pageable).stream()
													    .map(RecommendationDTO::fromEntity)
													    .collect(Collectors.toList()),
									HttpStatus.OK);
	}


	public ResponseEntity<?> findByUserId(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("Usuario n??o encontrado"));		
		
		return new ResponseEntity<>(user.getRecommendations().stream()
													  .map(RecommendationDTO::fromEntity)
													  .collect(Collectors.toList()),				
									HttpStatus.OK);	
	}


	public ResponseEntity<?> findByFilterByUserId(String userId, int page, int size) {
		User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("Usuario n??o encontrado"));		
		
		return new ResponseEntity<>(user.getRecommendations().stream()
													  .skip(page * size)
													  .limit(size)
													  .map(RecommendationDTO::fromEntity)
													  .collect(Collectors.toList()),				
									HttpStatus.OK);	
	}
 
}
