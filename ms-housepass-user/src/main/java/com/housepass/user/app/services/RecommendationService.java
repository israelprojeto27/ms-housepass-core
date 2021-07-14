package com.housepass.user.app.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.user.app.dtos.CreateRecomendationUserDTO;
import com.housepass.user.app.dtos.RecommendationDTO;
import com.housepass.user.app.dtos.UpdateStatusRecomendationUserDTO;
import com.housepass.user.app.entities.Recommendation;
import com.housepass.user.app.entities.User;
import com.housepass.user.app.entities.UserResume;
import com.housepass.user.app.enums.StatusEnum;
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

	
	@Transactional
	public ResponseEntity<?> create(CreateRecomendationUserDTO dto) {

		UserResume userResumeSendRecommendation = this.loadUserResume(dto.getUserSendRecommendationId()); // usuario que enviou a recomendacao
		User userReceiveRecommendation = userRepository.findById(dto.getUserReceiveRecommendationId()).get(); // usuario que recebeu a recomendacao
		
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
		
		return new ResponseEntity<>("Recomendação enviada para o usuário foi enviada com sucesso", HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(repository.findAll().stream()
													    .map(RecommendationDTO::fromEntity)
													    .collect(Collectors.toList()),
									HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<?> updateStatus(UpdateStatusRecomendationUserDTO dto) {
		Recommendation recommendation = repository.findById(dto.getRecommendationId()).get();
		
		if (! recommendation.getUserReceiveRecommendationId().equals(dto.getUserReceiveRecommendationId())) {			
			return new ResponseEntity<>("Não é possível atualizar status da Recomendação, pois usuario nao tem autorização para alterar", HttpStatus.BAD_REQUEST);
		}
		
		recommendation.setStatusEvaluation(dto.getStatusRecommendation());
		recommendation.setUpdatedDate(LocalDateTime.now());
		repository.save(recommendation);
		
		return new ResponseEntity<>("Recomendação atualizada com sucesso", HttpStatus.NO_CONTENT);
	}
	
	private UserResume loadUserResume(String userId) {
		UserResume userResume = userResumeRepository.findByUserId(userId); // usuario que enviou o convite
		if ( userResume == null ) {
			User user = userRepository.findById(userId).get(); // usuario que recebeu o convite
			userResume = UserResume.fromEntity(user);			
			userResumeRepository.insert(userResume);
			return userResume;
		}
		return userResume;
	}

}
