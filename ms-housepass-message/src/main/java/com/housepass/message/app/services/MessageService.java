package com.housepass.message.app.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.message.app.dtos.CreateFirstMessageDTO;
import com.housepass.message.app.dtos.CreateItemMessageDTO;

@Service
public class MessageService {

	@Transactional
	public ResponseEntity<?> create(CreateFirstMessageDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public ResponseEntity<?> addItemMessage(CreateItemMessageDTO dto) {
		// TODO Auto-generated method stub
		return null;
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
