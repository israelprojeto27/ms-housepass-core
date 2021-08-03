package com.housepass.message.app.services;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.message.app.dtos.CreateFirstMessageDTO;
import com.housepass.message.app.dtos.CreateItemMessageDTO;
import com.housepass.message.app.dtos.ItemMessageDTO;
import com.housepass.message.app.dtos.MessageDTO;
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
	private ItemMessageRepository itemMessageRepository;
	
	@Autowired
	private UserResumeRepository userResumeRepository;
	
	

	@Transactional
	public ResponseEntity<?> createFirstMessage(CreateFirstMessageDTO dto) {
		
		UserResume userResumeSend    = userResumeRepository.findByUserId(dto.getUserResumeSendId()).orElseThrow(() -> new DataNotFoundException ("Usuario que estã enviando a mensagem não foi encontrado"));	
		UserResume userResumeReceive = userResumeRepository.findByUserId(dto.getUserResumeReceiveId()).orElseThrow(() -> new DataNotFoundException ("Usuario que estã recebendo a mensagem não foi encontrado"));
		
		Message message = CreateFirstMessageDTO.toEntity(dto);	
		message.setUserResumeLastMessage(userResumeSend);
		repository.insert(message);
		
		ItemMessage itemMessage = ItemMessage.toEntity(message, userResumeSend);		
		itemMessageRepository.insert(itemMessage);
		
		message.setItemMessages(Arrays.asList(itemMessage));
		repository.save(message);			
		
		userResumeSend.setMessages(Arrays.asList(message));
		userResumeReceive.setMessages(Arrays.asList(message));
		
		userResumeRepository.save(userResumeSend);
		userResumeRepository.save(userResumeReceive);
		
		return new ResponseEntity<>("Primeira Mensagem entre os usuarios foi criada com sucesso", HttpStatus.CREATED);
	}

	@Transactional
	public ResponseEntity<?> addItemMessage(CreateItemMessageDTO dto) {

		UserResume userResumeSend = userResumeRepository.findByUserId(dto.getUserSendResumeId()).orElseThrow(() -> new DataNotFoundException ("Usuario que estã enviando a mensagem não foi encontrado"));
		
		ItemMessage itemMessage = CreateItemMessageDTO.toEntity(dto);		
		itemMessage.setUserResumeSend(userResumeSend);
		itemMessageRepository.insert(itemMessage);
		
		Message message = repository.findById(dto.getMessageId()).orElseThrow(() -> new DataNotFoundException ("Mensagem não foi encontrada"));
		message.setUserResumeLastMessage(userResumeSend);
		message.getItemMessages().add(itemMessage);
		
		repository.save(message);
		
		return new ResponseEntity<>("Mensagem foi criada com sucesso", HttpStatus.CREATED);
	}

	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(repository.findAll().stream()
													.map(MessageDTO::fromEntity)
													.collect(Collectors.toList()), 
									HttpStatus.OK);
	}

	public ResponseEntity<?> findAllMessagesByUserId(String userId) {
		
		UserResume userResume = userResumeRepository.findByUserId(userId).orElseThrow(() -> new DataNotFoundException ("Usuario que estã enviando a mensagem não foi encontrado"));
		
		return new ResponseEntity<>(userResume.getMessages().stream()
															.map(MessageDTO::fromEntity)
															.collect(Collectors.toList()), 
									HttpStatus.OK);
	}

	public ResponseEntity<?> findAllItemsMessagesByMessageId(String messageId) {
		Message message = repository.findById(messageId).orElseThrow(() -> new DataNotFoundException ("Mensagem não foi encontrada"));
		return new ResponseEntity<>(message.getItemMessages().stream()
															.map(ItemMessageDTO::fromEntity)
															.collect(Collectors.toList()), 
											HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<?> deleteMessageByMessageId(String messageId) {
		Message message = repository.findById(messageId).orElseThrow(() -> new DataNotFoundException ("Mensagem não foi encontrada"));
		itemMessageRepository.deleteAll(message.getItemMessages());
		repository.delete(message);
		return new ResponseEntity<>("Mensagem removida com sucesso", HttpStatus.NO_CONTENT);
	}

	@Transactional
	public ResponseEntity<?> deleteItemMessageByItemMessageId(String itemMessageId) {
		ItemMessage itemMessage = itemMessageRepository.findById(itemMessageId).orElseThrow(() -> new DataNotFoundException ("Item Mensagem não foi encontrada"));
		Message message = repository.findById(itemMessage.getMessageId()).orElseThrow(() -> new DataNotFoundException ("Mensagem não foi encontrada"));
		
		message.getItemMessages().remove(itemMessage);
		repository.save(message);
		
		itemMessageRepository.delete(itemMessage);
		return new ResponseEntity<>("Item mensagem foi removida com sucesso", HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<?> findByFilterAllMessages(int page, int size) {
		return new ResponseEntity<>(repository.findAll().stream()
														.skip(page * size)
														.limit(size)
														.map(MessageDTO::fromEntity)
														.collect(Collectors.toList()), 
											HttpStatus.OK);
	}

	public ResponseEntity<?> findByFilterAllMessagesByUserId(String userId, int page, int size) {
		UserResume userResume = userResumeRepository.findByUserId(userId).orElseThrow(() -> new DataNotFoundException ("Usuario que estã enviando a mensagem não foi encontrado"));
		
		return new ResponseEntity<>(userResume.getMessages().stream()
														.skip(page * size)
														.limit(size)
														.map(MessageDTO::fromEntity)
														.collect(Collectors.toList()), 
											HttpStatus.OK);
	}

	public ResponseEntity<?> findByFilterAllItemsMessagesByMessageId(String messageId, int page, int size) {
		Message message = repository.findById(messageId).orElseThrow(() -> new DataNotFoundException ("Mensagem não foi encontrada"));
		return new ResponseEntity<>(message.getItemMessages().stream()
															.skip(page * size)
															.limit(size)
															.map(ItemMessageDTO::fromEntity)
															.collect(Collectors.toList()), 
												HttpStatus.OK);
	}

	

}
