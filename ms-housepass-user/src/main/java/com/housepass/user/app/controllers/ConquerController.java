package com.housepass.user.app.controllers;

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

import com.housepass.user.app.dtos.CreateConquerUserDTO;
import com.housepass.user.app.services.ConquerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ConquerController", tags = "Conquer", description = "Endpoints para o documento de Conquistas realizadas pelo Usuario")
@RestController
@RequestMapping("/conquer")
public class ConquerController {
	
	@Autowired
	private ConquerService service;
	
	@ApiOperation(value = "Adição de nova conquista de um usuário")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateConquerUserDTO dto) {
		return service.create(dto);		
	}
	
	@ApiOperation(value = "Lista todas as conquistas cadastradas")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();		
	}
	
	@ApiOperation(value = "Filtrar as conquistas cadastradas")
	@PostMapping("/filter")
	public ResponseEntity<?> findByFilter(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilter(page, size);
	}
	
	@ApiOperation(value = "Buscar conquista por Id")
	@GetMapping("/{conquerId}")
	public ResponseEntity<?> findById(@PathVariable String conquerId) {
		return service.findById(conquerId);		
	}
	
	
	@ApiOperation(value = "Remove conquista por Id")
	@DeleteMapping("/{conquerId}")
	public ResponseEntity<?> delete(@PathVariable String conquerId) {
		return service.delete(conquerId);		
	}
	

}
