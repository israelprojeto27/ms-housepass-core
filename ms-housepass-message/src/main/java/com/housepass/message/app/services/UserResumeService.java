package com.housepass.message.app.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.message.app.dtos.CreateUserResumeDTO;
import com.housepass.message.app.dtos.UpdateUserResumeDTO;
import com.housepass.message.app.dtos.UserDTO;
import com.housepass.message.app.entities.UserResume;
import com.housepass.message.app.exceptions.DataNotFoundException;
import com.housepass.message.app.repositories.UserResumeRepository;



@Service
public class UserResumeService {
	
	@Autowired
	private UserResumeRepository repository;
	

	@Transactional
	public String addUserResume(CreateUserResumeDTO dto) {		
		UserResume userResume = CreateUserResumeDTO.toEntity(dto);
		repository.insert(userResume);		
		return "ok";
	}


	@Transactional
	public String updateUserResume(UpdateUserResumeDTO dto) {
		UserResume userResume = repository.findById(dto.getUserId()).orElseThrow(() -> new DataNotFoundException ("Usuario não encontrado"));	
		userResume.setUserImageUrl(dto.getUserImageUrl());
		userResume.setUserName(dto.getUserName());
		repository.save(userResume);	
		return "ok";		
	}


	@Transactional
	public String deleteUserResume(String userId) {		
		UserResume userResume = repository.findById(userId).orElseThrow(() -> new DataNotFoundException ("Usuario não encontrado"));
		repository.delete(userResume);	
		return "ok";	
	}


	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(repository.findAll().stream()
													.map(UserDTO::fromEntity)
													.collect(Collectors.toList()), 
									HttpStatus.OK);
	}

}
