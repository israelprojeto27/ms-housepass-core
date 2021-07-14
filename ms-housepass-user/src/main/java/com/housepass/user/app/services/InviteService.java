package com.housepass.user.app.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.user.app.dtos.CreateInviteDTO;
import com.housepass.user.app.dtos.InviteDTO;
import com.housepass.user.app.dtos.RecommendationDTO;
import com.housepass.user.app.dtos.UpdateStatusInviterDTO;
import com.housepass.user.app.entities.ConnectionUser;
import com.housepass.user.app.entities.Invite;
import com.housepass.user.app.entities.User;
import com.housepass.user.app.entities.UserResume;
import com.housepass.user.app.enums.StatusEnum;
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

	@Transactional
	public ResponseEntity<?> create(CreateInviteDTO dto) {
		
		UserResume userSendInvite = this.loadUserResume(dto.getUserSendInviteId());
		Invite invite = CreateInviteDTO.toEntity(dto);
		invite.setUserSendInvite(userSendInvite);
		repository.insert(invite);
		
		User userReceiveInvite = userRepository.findById(dto.getUserReceiveInviteId()).get(); // usuario que recebeu o convite
		
		if ( userReceiveInvite.getInvites() != null) {
			userReceiveInvite.getInvites().add(invite);
		}
		else {
			userReceiveInvite.setInvites(Arrays.asList(invite));
		}		
		userRepository.save(userReceiveInvite);
		
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
		Invite invite = repository.findById(dto.getInviteId()).get();		
		
		if (! invite.getUserReceiveInviteId().equals(dto.getUserReceiveInviteId())) {			
			return new ResponseEntity<>("Não é possível atualizar status do Convite, pois usuario nao tem autorização para alterar", HttpStatus.BAD_REQUEST);
		}
		
		if (dto.getStatusInvite().equals(StatusEnum.ACCEPTED)) { // o convite aceito deve virar uma conexao		
			UserResume userResumeSendInvite =  invite.getUserSendInvite();
			UserResume userResumeReceiveInvite = loadUserResume(invite.getUserReceiveInviteId());
			
			User userSendInvite = userRepository.findById(invite.getUserSendInvite().getUserId()).get();
			ConnectionUser user1 =  ConnectionUser.fromEntity(userResumeReceiveInvite);
			connectionUserRepository.insert(user1); 
			
			if ( userSendInvite.getConnections() != null ) {
				userSendInvite.getConnections().add(user1);
			}
			else {
				userSendInvite.setConnections(Arrays.asList(user1));
			}						
			
			User userRceiveInvite = userRepository.findById(invite.getUserReceiveInviteId()).get();
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
			
		}
		else if (dto.getStatusInvite().equals(StatusEnum.REJECTED)) {
			User userRceiveInvite = userRepository.findById(invite.getUserReceiveInviteId()).get();
			userRceiveInvite.getInvites().remove(invite);
			userRepository.save(userRceiveInvite);
			
			repository.delete(invite);			
			
		}
		
		return new ResponseEntity<>("Convite atualizado com sucesso", HttpStatus.NO_CONTENT);
	}
	
	private UserResume loadUserResume(String userId) {
		UserResume userResume = userResumeRepository.findByUserId(userId); // usuario que enviou o convite
		if ( userResume == null ) {
			User user = userRepository.findById(userId).get(); // usuario que recebeu o convite
			userResume = UserResume.fromEntity(user);			
			userResumeRepository.insert(userResume);
			return userResume;
		}
		return userResume;
	}

}
