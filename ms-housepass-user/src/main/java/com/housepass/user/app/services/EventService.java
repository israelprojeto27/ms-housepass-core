package com.housepass.user.app.services;

import java.time.LocalTime;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.user.app.dtos.ConquerDTO;
import com.housepass.user.app.dtos.CreateEventDTO;
import com.housepass.user.app.dtos.EventDTO;
import com.housepass.user.app.dtos.UpdateEventDTO;
import com.housepass.user.app.entities.Event;
import com.housepass.user.app.entities.User;
import com.housepass.user.app.entities.UserResume;
import com.housepass.user.app.repositories.EventRepository;
import com.housepass.user.app.repositories.UserRepository;
import com.housepass.user.app.repositories.UserResumeRepository;
import com.housepass.user.app.utils.FormatUtils;

import com.housepass.user.app.exceptions.DataNotFoundException;


@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserResumeRepository userResumeRepository;

	@Transactional
	public ResponseEntity<?> create(CreateEventDTO dto) {
		Event event = CreateEventDTO.toEntity(dto);
		UserResume userResume = userResumeRepository.findByUserId( dto.getUserCreateEventId());
		event.setUserCreateEvent(userResume);
		repository.insert(event);
		
		User user = userRepository.findById(dto.getUserCreateEventId()).get();
		if ( user.getEvents() != null ) {
			user.getEvents().add(event);
		}
		else {
			user.setEvents(Arrays.asList(event));
		}
		userRepository.save(user);
		
		return new ResponseEntity<>("Evento foi criado com sucesso", HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> findAll() {		 
		return new ResponseEntity<>(repository.findAll().stream()
													    .map(EventDTO::fromEntity)
													    .collect(Collectors.toList()),
									HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<?> deleteById(String eventId) {
		
		Event event = repository.findById(eventId).orElseThrow(() -> new DataNotFoundException("Evento não encontrado"));
		User user = userRepository.findById(event.getUserCreateEvent().getUserId()).orElseThrow(() -> new DataNotFoundException("Usuario não encontrado ou já deletado"));
		
		user.getEvents().remove(event);
		userRepository.save(user);		
		repository.delete(event);
		
		return new ResponseEntity<>("Evento foi removido com sucesso", HttpStatus.NO_CONTENT);
	}

	
	public ResponseEntity<?> findById(String eventId) {
		Event event = repository.findById(eventId).orElseThrow(() -> new DataNotFoundException("Evento não encontrado"));	
		return new ResponseEntity<>(EventDTO.fromEntity(event), HttpStatus.OK);
	}
	
	

	@Transactional
	public ResponseEntity<?> update(String eventId, UpdateEventDTO dto) {
		Event event = repository.findById(eventId).orElseThrow(() -> new DataNotFoundException("Evento não encontrado"));
		event.setTitle(dto.getTitle());
		event.setDescrption(dto.getDescrption());
		event.setLocation(dto.getLocation());
		event.setDate(FormatUtils.converteStringToLocalDateTime(dto.getDate()));
		event.setHour(LocalTime.parse(dto.getHour()));
		event.setImageUrl(dto.getImageUrl());
		
		repository.save(event);
		
		return new ResponseEntity<>("Evento atualizado com sucesso", HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<?> findByFilter(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("createdDate").descending());
		return new ResponseEntity<>(repository.findAll(pageable).stream()
													    .map(EventDTO::fromEntity)
													    .collect(Collectors.toList()),
										HttpStatus.OK);
	}

	public ResponseEntity<?> findByUserId(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("Usuario não encontrado"));		
		
		return new ResponseEntity<>(user.getEvents().stream()
													  .map(EventDTO::fromEntity)
													  .collect(Collectors.toList()),				
									HttpStatus.OK);	
	}

	public ResponseEntity<?> findByFilterByUserId(String userId, int page, int size) {
		User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("Usuario não encontrado"));		
		
		return new ResponseEntity<>(user.getEvents().stream()
													  .skip(page * size)
				  									  .limit(size)
													  .map(EventDTO::fromEntity)
													  .collect(Collectors.toList()),				
									HttpStatus.OK);	
	} 

}
