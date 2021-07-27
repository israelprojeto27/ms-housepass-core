package com.housepass.user.app.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.user.app.dtos.ConquerDTO;
import com.housepass.user.app.dtos.CreateInviteDTO;
import com.housepass.user.app.dtos.CreateNotificationDTO;
import com.housepass.user.app.dtos.InviteDTO;
import com.housepass.user.app.dtos.RecommendationDTO;
import com.housepass.user.app.dtos.UpdateStatusInviterDTO;
import com.housepass.user.app.entities.ConnectionUser;
import com.housepass.user.app.entities.Invite;
import com.housepass.user.app.entities.User;
import com.housepass.user.app.entities.UserResume;
import com.housepass.user.app.enums.NotificationType;
import com.housepass.user.app.enums.StatusEnum;
import com.housepass.user.app.exceptions.DataNotFoundException;
import com.housepass.user.app.feignClient.NotificationClient;
import com.housepass.user.app.repositories.ConnectionUserRepository;
import com.housepass.user.app.repositories.InviteRepository;
import com.housepass.user.app.repositories.UserRepository;
import com.housepass.user.app.repositories.UserResumeRepository;

@Service
public class InviteService {
	
	@Autowired
	private InviteRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserResumeRepository userResumeRepository;
	
	@Autowired
	private ConnectionUserRepository connectionUserRepository;
	
	
	@Autowired
	private NotificationClient notificationClient;

	@Transactional
	public ResponseEntity<?> create(CreateInviteDTO dto) {
		
		UserResume userSendInvite = userResumeRepository.findByUserId(dto.getUserSendInviteId());
		Invite invite = CreateInviteDTO.toEntity(dto);
		invite.setUserSendInvite(userSendInvite);
		repository.insert(invite);
		
		User userReceiveInvite = userRepository.findById(dto.getUserReceiveInviteId()).orElseThrow(() -> new DataNotFoundException("Usuário não encontrado"));
		
		if ( userReceiveInvite.getInvites() != null) {
			userReceiveInvite.getInvites().add(invite);
		}
		else {
			userReceiveInvite.setInvites(Arrays.asList(invite));
		}		
		userRepository.save(userReceiveInvite);
		
		CreateNotificationDTO createNotificationDTO = new CreateNotificationDTO();
		createNotificationDTO.setDescription("Um novo convite foi enviado pelo usuario: " + userSendInvite.getUserName());
		createNotificationDTO.setType(NotificationType.INVITE);
		createNotificationDTO.setUserId(userReceiveInvite.getId());
		createNotificationDTO.setUserSendId(userSendInvite.getUserId());
		notificationClient.addNotification(createNotificationDTO);
		
		return new ResponseEntity<>("Convite foi enviado para o usuário com sucesso", HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> findAll() {		 
		return new ResponseEntity<>(repository.findAll().stream()
													    .map(InviteDTO::fromEntity)
													    .collect(Collectors.toList()),
									HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<?> updateStatus(UpdateStatusInviterDTO dto) {
		Invite invite = repository.findById(dto.getInviteId()).orElseThrow(() -> new DataNotFoundException("Convite não encontrado"));
		
		if (! invite.getUserReceiveInviteId().equals(dto.getUserReceiveInviteId())) {			
			return new ResponseEntity<>("Não é possível atualizar status do Convite, pois usuario nao tem autorização para alterar", HttpStatus.BAD_REQUEST);
		}
		
		if (dto.getStatusInvite().equals(StatusEnum.ACCEPTED)) { // o convite aceito deve virar uma conexao		
			UserResume userResumeSendInvite =  invite.getUserSendInvite();
			UserResume userResumeReceiveInvite = userResumeRepository.findByUserId(invite.getUserReceiveInviteId());
			
			User userSendInvite = userRepository.findById(invite.getUserSendInvite().getUserId()).orElseThrow(() -> new DataNotFoundException("Usuário não encontrado"));
			ConnectionUser user1 =  ConnectionUser.fromEntity(userResumeReceiveInvite);
			connectionUserRepository.insert(user1); 
			
			if ( userSendInvite.getConnections() != null ) {
				userSendInvite.getConnections().add(user1);
			}
			else {
				userSendInvite.setConnections(Arrays.asList(user1));
			}						
			
			User userRceiveInvite = userRepository.findById(invite.getUserReceiveInviteId()).orElseThrow(() -> new DataNotFoundException("Usuário não encontrado"));
			ConnectionUser user2 =  ConnectionUser.fromEntity(userResumeSendInvite);
			connectionUserRepository.insert(user2); 
			
			if ( userRceiveInvite.getConnections() != null ) {
				userRceiveInvite.getConnections().add(user2);
			}
			else {
				userRceiveInvite.setConnections(Arrays.asList(user2));				
			}
			
			userRepository.save(userSendInvite);
			userRepository.save(userRceiveInvite);
			
			invite.setStatusInvite(StatusEnum.ACCEPTED);
			invite.setUpdatedDate(LocalDateTime.now());
			repository.save(invite);
			
			CreateNotificationDTO createNotificationDTO = new CreateNotificationDTO();
			createNotificationDTO.setDescription("O seu convite foi aceito pelo usuario: " + userRceiveInvite.getName());
			createNotificationDTO.setType(NotificationType.ACCEPTED_INVITE);
			createNotificationDTO.setUserId(userSendInvite.getId());
			createNotificationDTO.setUserSendId(userRceiveInvite.getId());
			notificationClient.addNotification(createNotificationDTO); 
			
		}
		else if (dto.getStatusInvite().equals(StatusEnum.REJECTED)) {
			User userRceiveInvite = userRepository.findById(invite.getUserReceiveInviteId()).orElseThrow(() -> new DataNotFoundException("Usuário não encontrado"));
			userRceiveInvite.getInvites().remove(invite);
			userRepository.save(userRceiveInvite);
			
			repository.delete(invite);
		}
		
		return new ResponseEntity<>("Convite atualizado com sucesso", HttpStatus.NO_CONTENT);
	}
	
	
	@Transactional
	public ResponseEntity<?> deleteById(String inviteId) {		
		Invite invite = repository.findById(inviteId).orElseThrow(() -> new DataNotFoundException("Convite não encontrado"));
		User user = userRepository.findById(invite.getUserReceiveInviteId()).orElseThrow(() -> new DataNotFoundException("Usuário não encontrado"));
		
		user.getInvites().remove(invite);
		userRepository.save(user);		
		repository.delete(invite);
		
		return new ResponseEntity<>("Convite removido com sucesso", HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<?> findByFilter(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("createdDate").descending());
		return new ResponseEntity<>(repository.findAll(pageable).stream()
													    .map(InviteDTO::fromEntity)
													    .collect(Collectors.toList()),
										HttpStatus.OK);
	}

	public ResponseEntity<?> findByUserId(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("Usuario não encontrado"));		
		
		return new ResponseEntity<>(user.getInvites().stream()
													  .map(InviteDTO::fromEntity)
													  .collect(Collectors.toList()),				
									HttpStatus.OK);	
	}

	public ResponseEntity<?> findByFilterByUserId(String userId, int page, int size) {
		User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("Usuario não encontrado"));		
		
		return new ResponseEntity<>(user.getInvites().stream()
													  .skip(page * size)
													  .limit(size)
													  .map(InviteDTO::fromEntity)
													  .collect(Collectors.toList()),				
									HttpStatus.OK);	
	} 
}
