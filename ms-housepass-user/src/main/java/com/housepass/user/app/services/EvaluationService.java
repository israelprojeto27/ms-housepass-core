package com.housepass.user.app.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.user.app.dtos.CreateEvaluationUserDTO;
import com.housepass.user.app.dtos.EvaluationDTO;
import com.housepass.user.app.entities.Evaluation;
import com.housepass.user.app.entities.User;
import com.housepass.user.app.entities.UserResume;
import com.housepass.user.app.repositories.EvaluationRepository;
import com.housepass.user.app.repositories.UserRepository;
import com.housepass.user.app.repositories.UserResumeRepository;

@Service
public class EvaluationService {
	
	@Autowired
	private EvaluationRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserResumeRepository userResumeRepository;

	@Transactional
	public ResponseEntity<?> create( CreateEvaluationUserDTO dto) {
		
		UserResume userResumeEvaluator = this.loadUserResume(dto.getUserEvaluatorId());
		User userReceiveEvaluation = userRepository.findById(dto.getUserReceiveEvaluationId()).get(); // usuario que recebeu a avaliação
						
		Evaluation eval = CreateEvaluationUserDTO.toEntity(dto);
		eval.setUserEvaluator(userResumeEvaluator);
		repository.insert(eval);
		
		if ( userReceiveEvaluation.getEvaluations() != null) {
			userReceiveEvaluation.getEvaluations().add(eval);
		}
		else {
			userReceiveEvaluation.setEvaluations(Arrays.asList(eval));
		}		
		userRepository.save(userReceiveEvaluation);
		
		return new ResponseEntity<>("Avaliação foi enviada para o usuário com sucesso", HttpStatus.CREATED);
	}
 

	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(repository.findAll().stream()
												 	   .map(EvaluationDTO::fromEntity)
												       .collect(Collectors.toList()),				
									HttpStatus.OK);
	}
	
	
	
	private UserResume loadUserResume(String userId) {
		UserResume userResume = userResumeRepository.findByUserId(userId); 
		if ( userResume == null ) {
			User user = userRepository.findById(userId).get(); 
			userResume = UserResume.fromEntity(user);			
			userResumeRepository.insert(userResume);
			return userResume;
		}
		return userResume;
	}

}
