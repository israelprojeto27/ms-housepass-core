package com.housepass.imoveis.app.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.imoveis.app.dtos.CreateUserResumeImovellDTO;
import com.housepass.imoveis.app.dtos.OfertaDTO;
import com.housepass.imoveis.app.dtos.UserResumeDTO;
import com.housepass.imoveis.app.entities.UserResume;
import com.housepass.imoveis.app.repositories.UserResumeRepository;
import com.housepass.imoveis.app.exceptions.DataNotFoundException;

@Service
public class UserResumeService {

	@Autowired
	private UserResumeRepository repository;
	

	@Transactional
	public String add(CreateUserResumeImovellDTO dto) {
		UserResume userResume = CreateUserResumeImovellDTO.toEntity(dto);
		repository.insert(userResume);
		return "ok";
	}

	@Transactional
	public String delete(String userId) {
		UserResume userResume = repository.findByUserId(userId);
		repository.delete(userResume);
		return "ok";
	}

	public ResponseEntity<?> findAll() {		
		return new ResponseEntity<>(repository.findAll().stream()
														.map(UserResumeDTO::fromEntity)
														.collect(Collectors.toList()), 
									HttpStatus.OK);
	}
	
	

	public String findById(String userId) {
		UserResume userResume = repository.findByUserId(userId);
		if (userResume != null ) {
			return "S";
		}
		else {
			return "N";
		}		
	}
	
	
}
