package com.housepass.user.app.services;

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

import com.housepass.user.app.dtos.CreateEvaluationUserDTO;
import com.housepass.user.app.dtos.EvaluationDTO;
import com.housepass.user.app.entities.Evaluation;
import com.housepass.user.app.entities.User;
import com.housepass.user.app.entities.UserResume;
import com.housepass.user.app.exceptions.DataNotFoundException;
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
		
		UserResume userResumeEvaluator = userResumeRepository.findByUserId(dto.getUserEvaluatorId());
		User userReceiveEvaluation = userRepository.findById(dto.getUserReceiveEvaluationId()).orElseThrow(() -> new DataNotFoundException("Usuário não encontrado"));
						
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
	
	@Transactional
	public ResponseEntity<?> deleteById(String evaluationId) {
		
		Evaluation eval = repository.findById(evaluationId).orElseThrow(() -> new DataNotFoundException("Avaliação não encontrada"));
		User user = userRepository.findById(eval.getUserReceiveEvaluationId()).orElseThrow(() -> new DataNotFoundException("Usuario não encontrado ou já deletado"));
		
		user.getEvaluations().remove(eval);
		userRepository.save(user);	
		repository.delete(eval);
		
		return new ResponseEntity<>("Avaliação foi removida com sucesso", HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<?> findById(String evaluationId) {
		Evaluation eval = repository.findById(evaluationId).orElseThrow(() -> new DataNotFoundException("Avaliação não encontrada"));		
		return new ResponseEntity<>(EvaluationDTO.fromEntity(eval), HttpStatus.OK);
	}



	public ResponseEntity<?> findByFilter(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("createdDate").descending());	
		return new ResponseEntity<>(repository.findAll(pageable).stream()
													 	.map(EvaluationDTO::fromEntity)
													    .collect(Collectors.toList()),				
										HttpStatus.OK);
	}
 

}
