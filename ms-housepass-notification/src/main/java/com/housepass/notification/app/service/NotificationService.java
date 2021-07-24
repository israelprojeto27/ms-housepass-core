package com.housepass.notification.app.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.notification.app.dtos.CreateNotificationDTO;
import com.housepass.notification.app.dtos.NotificationDTO;
import com.housepass.notification.app.entities.Notification;
import com.housepass.notification.app.entities.UserResume;
import com.housepass.notification.app.exceptions.DataNotFoundException;
import com.housepass.notification.app.repositories.NotificationRepository;
import com.housepass.notification.app.repositories.UserResumeRepository;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository repository;
	
	@Autowired
	private UserResumeRepository userResumeRepository;	
	

	@Transactional
	public ResponseEntity<?> create(CreateNotificationDTO dto) {
		
		Notification notification = CreateNotificationDTO.toEntity(dto);
		UserResume userResume = userResumeRepository.findByUserId(dto.getUserId());
		UserResume userSendResume = userResumeRepository.findByUserId(dto.getUserSendId());
		notification.setUser(userResume);
		notification.setUserSend(userSendResume);
		repository.insert(notification);
		
		userResume.getNotifications().add(notification);
		userResumeRepository.save(userResume);
		
		return new ResponseEntity<>("Notificação criada com sucesso", HttpStatus.CREATED);
	}


	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(repository.findAll().stream()
													.map(NotificationDTO::fromEntity)
													.collect(Collectors.toList()), 
									HttpStatus.OK);
	}


	public ResponseEntity<?> findByFilter(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("createdDate").descending());
		return new ResponseEntity<>(repository.findAll(pageable).stream()
											  .map(NotificationDTO::fromEntity)
											  .collect(Collectors.toList()), 
				HttpStatus.OK);
	}


	public ResponseEntity<?> findById(String notificationId) {
		Notification notification = repository.findById(notificationId).orElseThrow(() -> new DataNotFoundException("Notificação não encontrada"));
		return new ResponseEntity<>(NotificationDTO.fromEntity(notification), HttpStatus.OK);
	}

	
	@Transactional
	public ResponseEntity<?> delete(String notificationId) {
		Notification notification = repository.findById(notificationId).orElseThrow(() -> new DataNotFoundException("Notificação não encontrada"));		
		UserResume userResume = notification.getUser();
		
		userResume.getNotifications().remove(notification);
		userResumeRepository.save(userResume);
		
		repository.delete(notification);
		return new ResponseEntity<>("Notificação foi removida com sucesso", HttpStatus.NO_CONTENT);
	}


	public ResponseEntity<?> findByUserId(String userId) {
		UserResume userResume = userResumeRepository.findByUserId(userId);		
		return new ResponseEntity<>(userResume.getNotifications().stream()
														.map(NotificationDTO::fromEntity)
														.collect(Collectors.toList()), 
										HttpStatus.OK);
	}


	public ResponseEntity<?> findByFilterByUserId(String userId, int page, int size) {
		UserResume userResume = userResumeRepository.findByUserId(userId);		
		return new ResponseEntity<>(userResume.getNotifications().stream()
														.skip(page * size)
														.limit(size)
														.map(NotificationDTO::fromEntity)
														.collect(Collectors.toList()), 
										HttpStatus.OK);
	}

}
