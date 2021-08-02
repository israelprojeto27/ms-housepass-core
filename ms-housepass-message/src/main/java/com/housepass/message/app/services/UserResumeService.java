package com.housepass.message.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.message.app.dtos.CreateUserResumeDTO;
import com.housepass.message.app.dtos.UpdateUserResumeDTO;
import com.housepass.message.app.entities.UserResume;
import com.housepass.message.app.exceptions.DataNotFoundException;
import com.housepass.message.app.repositories.UserResumeRepository;


@Service
public class UserResumeService {
	
	@Autowired
	private UserResumeRepository userResumeRepository;
	

	@Transactional
	public String addUserResume(CreateUserResumeDTO dto) {		
		UserResume userResume = CreateUserResumeDTO.toEntity(dto);
		userResumeRepository.insert(userResume);		
		return "ok";
	}


	@Transactional
	public String updateUserResume(UpdateUserResumeDTO dto) {
		UserResume userResume = userResumeRepository.findById(dto.getUserId()).orElseThrow(() -> new DataNotFoundException ("Usuario não encontrado"));	
		userResume.setUserImageUrl(dto.getUserImageUrl());
		userResume.setUserName(dto.getUserName());
		userResumeRepository.save(userResume);	
		return "ok";		
	}


	@Transactional
	public String deleteUserResume(String userId) {		
		UserResume userResume = userResumeRepository.findById(userId).orElseThrow(() -> new DataNotFoundException ("Usuario não encontrado"));
		userResumeRepository.delete(userResume);	
		return "ok";	
	}

}
