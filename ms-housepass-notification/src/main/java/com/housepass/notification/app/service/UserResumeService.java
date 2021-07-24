package com.housepass.notification.app.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.housepass.notification.app.dtos.CreateUserResumeNotificationlDTO;
import com.housepass.notification.app.dtos.UserResumeDTO;
import com.housepass.notification.app.entities.UserResume;
import com.housepass.notification.app.repositories.UserResumeRepository;


@Service
public class UserResumeService {
	
	
	@Autowired
	private UserResumeRepository repository; 

	@Transactional
	public String add(CreateUserResumeNotificationlDTO dto) {
		UserResume userResume = CreateUserResumeNotificationlDTO.toEntity(dto);
		repository.insert(userResume);
		return "ok";
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

}
