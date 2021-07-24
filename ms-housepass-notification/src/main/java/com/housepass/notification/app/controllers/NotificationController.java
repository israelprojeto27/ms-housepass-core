package com.housepass.notification.app.controllers;

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

import com.housepass.notification.app.dtos.CreateNotificationDTO;
import com.housepass.notification.app.service.NotificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "NotificationController", tags = "Notification", description = "REST Api de MS Notification")
@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired
	private NotificationService service;
	
	@ApiOperation(value = "Cadastro de uma nova notificação")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateNotificationDTO dto){
		return service.create(dto);		
	}
	
	@ApiOperation(value = "Buscar todass as notificações cadastradas")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();
	}
	
	
	@ApiOperation(value = "Filtrar as notificações cadastradas")
	@PostMapping("/filter")
	public ResponseEntity<?> findByFilter(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilter(page, size);
	}
	
	
	@ApiOperation(value = "Buscar notificação por Id")
	@GetMapping("/{notificationId}")
	public ResponseEntity<?> findById(@PathVariable String notificationId) {
		return service.findById(notificationId);
	}
	
	
	@ApiOperation(value = "Deletar notificação por Id")
	@DeleteMapping("/{notificationId}")
	public ResponseEntity<?> delete(@PathVariable String notificationId) {
		return service.delete(notificationId);
	}
	
	@ApiOperation(value = "Buscar notificações por User Id")
	@GetMapping("/findByUser/{userId}")
	public ResponseEntity<?> findByUserId(@PathVariable String userId) {
		return service.findByUserId(userId);
	}
	
	@ApiOperation(value = "Paginar notificações por User Id")
	@GetMapping("/filterByUser/{userId}")
	public ResponseEntity<?> findByFilterByUserId(@PathVariable String userId, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilterByUserId(userId, page, size);
	}
	

}
