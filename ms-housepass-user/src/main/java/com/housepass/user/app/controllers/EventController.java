package com.housepass.user.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.user.app.dtos.CreateEventDTO;
import com.housepass.user.app.dtos.UpdateEventDTO;
import com.housepass.user.app.services.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "EventController", tags = "Event", description = "Endpoints para o documento de Eventos criados por um usuario")
@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventService service;
	
	@ApiOperation(value = "Cria um evento por um usuario")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateEventDTO dto) {
		return service.create(dto);		
	}
	
	@ApiOperation(value = "Lista todos os eventos cadastrados")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();		
	}
	
	@ApiOperation(value = "Filtrar os eventos cadastrados")
	@PostMapping("/filter")
	public ResponseEntity<?> findByFilter(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilter(page, size);
	}
	

	@ApiOperation(value = "Buscar um evento por Id")
	@GetMapping("/{eventId}")
	public ResponseEntity<?> findEventById(@PathVariable String eventId) {
		return service.findById(eventId);		
	}
	
	
	@ApiOperation(value = "Deleta um evento por Id")
	@DeleteMapping("/{eventId}")
	public ResponseEntity<?> delete(@PathVariable String eventId) {
		return service.deleteById(eventId);		
	}
	
	
	@ApiOperation(value = "Atualiza informações de um evento por Id")
	@PatchMapping("/{eventId}")
	public ResponseEntity<?> update(@PathVariable String eventId, 
									@RequestBody UpdateEventDTO dto) {
		
		return service.update(eventId, dto);		
	}

}
