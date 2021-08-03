package com.housepass.message.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.message.app.dtos.CreateFirstMessageDTO;
import com.housepass.message.app.dtos.CreateItemMessageDTO;
import com.housepass.message.app.services.MessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "MessageController", tags = "Message", description = "Endpoints para o documento de Message")
@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService service;
	
	@ApiOperation(value = "Cria primeira vez uma canal de message entre 2 usuarios")
	@PostMapping
	public ResponseEntity<?> createFirstMessage(@RequestBody CreateFirstMessageDTO dto) {
		return service.createFirstMessage(dto);
	}
	
	
	@ApiOperation(value = "Adiciona Item Message a uma Message")
	@PostMapping("/addItemMessage")
	public ResponseEntity<?> addItemMessage(@RequestBody CreateItemMessageDTO dto) {
		return service.addItemMessage(dto);
	}
	
	@ApiOperation(value = "Listar todas mensages")
	@GetMapping
	public ResponseEntity<?> findAll() {
		 return service.findAll();
	}
	
	
	@ApiOperation(value = "Filtrar todas mensagens cadastradas")
	@PostMapping("/findByFilterAllMessages")
	public ResponseEntity<?> findByFilterAllMessages(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilterAllMessages(page, size);
	}
	
	
	@ApiOperation(value = "Listar todas mensages por user Id")
	@GetMapping("/findAllMessagesByUserId/{userId}")
	public ResponseEntity<?> findAllMessagesByUserId(@PathVariable String  userId) {
		 return service.findAllMessagesByUserId(userId);
	}
	
	
	@ApiOperation(value = "Filtrar todas mensagens cadastradas por user Id")
	@PostMapping("/findByFilterAllMessagesByUserId/{userId}")
	public ResponseEntity<?> findByFilterAllMessagesByUserId(@PathVariable String  userId, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilterAllMessagesByUserId(userId, page, size);
	}
	
	
	@ApiOperation(value = "Listar todas itens mensages por message Id")
	@GetMapping("/findAllItemsMessagesByUserId/{messageId}")
	public ResponseEntity<?> findAllItemsMessagesByMessageId(@PathVariable String  messageId) {
		 return service.findAllItemsMessagesByMessageId(messageId);
	}
	
	
	@ApiOperation(value = "Filtrar todas itens mensagens cadastradas por message Id")
	@PostMapping("/findByFilterAllItemsMessagesByMessageId/{messageId}")
	public ResponseEntity<?> findByFilterAllItemsMessagesByMessageId(@PathVariable String  messageId, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilterAllItemsMessagesByMessageId(messageId, page, size);
	}
		
	
	@ApiOperation(value = "Delete message por message Id")
	@DeleteMapping("/deleteMessageByMessageId/{messageId}")
	public ResponseEntity<?> deleteMessageByMessageId(@PathVariable String  messageId) {
		 return service.deleteMessageByMessageId(messageId);
	}
	
	
	
	@ApiOperation(value = "Delete item message por item message Id")
	@DeleteMapping("/deleteItemMessageByItemMessageId/{itemMessageId}")
	public ResponseEntity<?> deleteItemMessageByItemMessageId(@PathVariable String  itemMessageId) {
		 return service.deleteItemMessageByItemMessageId(itemMessageId);
	}

}
