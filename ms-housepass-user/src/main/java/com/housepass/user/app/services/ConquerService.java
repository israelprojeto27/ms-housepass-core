package com.housepass.user.app.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.user.app.dtos.ConquerDTO;
import com.housepass.user.app.dtos.CreateConquerUserDTO;
import com.housepass.user.app.entities.Conquer;
import com.housepass.user.app.entities.User;
import com.housepass.user.app.exceptions.DataNotFoundException;
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
		return new ResponseEntity<>(repository.findAll().stream()
														.map(ConquerDTO::fromEntity)
														.collect(Collectors.toList()),				
														HttpStatus.OK);		
	}

	
	public ResponseEntity<?> findById(String conquerId) {
		Conquer conquer = repository.findById(conquerId).orElseThrow(() -> new DataNotFoundException("Conquista não encontrada"));
		return new ResponseEntity<>(ConquerDTO.fromEntity(conquer), HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<?> delete(String conquerId) {
		Conquer conquer = repository.findById(conquerId).orElseThrow(() -> new DataNotFoundException("Conquista não encontrada"));
		repository.delete(conquer);
		return new ResponseEntity<>("Conquista removida com sucesso", HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<?> findByFilter(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("createdDate").descending());	
		return new ResponseEntity<>(repository.findAll(pageable).stream()
														.map(ConquerDTO::fromEntity)
														.collect(Collectors.toList()),				
									HttpStatus.OK);	
	}

}
