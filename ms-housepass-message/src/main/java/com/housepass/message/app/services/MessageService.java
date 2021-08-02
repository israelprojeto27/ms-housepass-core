package com.housepass.message.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.message.app.dtos.CreateFirstMessageDTO;
import com.housepass.message.app.dtos.CreateItemMessageDTO;
import com.housepass.message.app.entities.ItemMessage;
import com.housepass.message.app.entities.Message;
import com.housepass.message.app.entities.UserResume;
import com.housepass.message.app.exceptions.DataNotFoundException;
import com.housepass.message.app.repositories.ItemMessageRepository;
import com.housepass.message.app.repositories.MessageRepository;
import com.housepass.message.app.repositories.UserResumeRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository repository;
	
	@Autowired
	private ItemMessageRepository itemMessagerepository;
	
	@Autowired
	private UserResumeRepository userResumeRepository;
	
	

	@Transactional
	public ResponseEntity<?> createFirstMessage(CreateFirstMessageDTO dto) {
		
		Message message = CreateFirstMessageDTO.toEntity(dto);		
		repository.insert(message);
		
		ItemMessage itemMessage = ItemMessage.toEntity(message); 
		itemMessagerepository.insert(itemMessage);
		
		UserResume userResumeSend    = userResumeRepository.findById(dto.getUserResumeSendId()).orElseThrow(() -> new DataNotFoundException ("Usuario que estã enviando a mensagem não foi encontrado"));	
		UserResume userResumeReceive = userResumeRepository.findById(dto.getUserResumeReceiveId()).orElseThrow(() -> new DataNotFoundException ("Usuario que estã recebendo a mensagem não foi encontrado"));
		
		userResumeSend.getMessages().add(message);
		userResumeReceive.getMessages().add(message);
		
		userResumeRepository.save(userResumeSend);
		userResumeRepository.save(userResumeReceive);
		
		return new ResponseEntity<>("Primeira Mensagem entre os usuarios foi criada com sucesso", HttpStatus.CREATED);
	}

	@Transactional
	public ResponseEntity<?> addItemMessage(CreateItemMessageDTO dto) {

		ItemMessage itemMessage = CreateItemMessageDTO.toEntity(dto);
		itemMessagerepository.insert(itemMessage);
		
		Message message = repository.findById(dto.getMessageId()).orElseThrow(() -> new DataNotFoundException ("Mensagem não foi encontrada"));
		message.getItemMessages().add(itemMessage);
		repository.save(message);
		
		return new ResponseEntity<>("Mensagem foi criada com sucesso", HttpStatus.CREATED);
	}

	public ResponseEntity<?> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<?> findAllMessagesByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<?> findAllItemsMessagesByMessageId(String messageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public ResponseEntity<?> deleteMessageByMessageId(String messageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public ResponseEntity<?> deleteItemMessageByItemMessageId(String itemMessageId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<?> findByFilterAllMessages(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<?> findByFilterAllMessagesByUserId(String userId, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<?> findByFilterAllMessagesByMessageId(String messageId, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
