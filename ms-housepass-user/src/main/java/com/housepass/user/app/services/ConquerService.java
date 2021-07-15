package com.housepass.user.app.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.user.app.dtos.ConquerDTO;
import com.housepass.user.app.dtos.CreateConquerUserDTO;
import com.housepass.user.app.entities.Conquer;
import com.housepass.user.app.entities.User;
import com.housepass.user.app.repositories.ConquerRepository;
import com.housepass.user.app.repositories.UserRepository;

@Service
public class ConquerService {
	
	@Autowired
	private ConquerRepository repository;
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public ResponseEntity<?> create(CreateConquerUserDTO dto) {
		
		Conquer conquer = CreateConquerUserDTO.toEntity(dto);
		repository.insert(conquer);
		
		User user = userRepository.findById(dto.getUserId()).get();
		if (user.getConquers() != null) {
			user.getConquers().add(conquer);
		}
		else {
			user.setConquers(Arrays.asList(conquer));
		}
		
		userRepository.save(user);
		
		return new ResponseEntity<>("Conquista do usuário foi adicionada com sucesso", HttpStatus.CREATED);
	}

	public ResponseEntity<?> findAll() {
		List<Conquer> list = repository.findAll();
		return new ResponseEntity<>(list.stream()
										.map(ConquerDTO::fromEntity)
										.collect(Collectors.toList()),				
									HttpStatus.OK);
		
	}

}
