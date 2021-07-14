package com.housepass.user.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.user.app.dtos.CreateRecomendationUserDTO;
import com.housepass.user.app.dtos.UpdateStatusRecomendationUserDTO;
import com.housepass.user.app.services.RecommendationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "RecommendationController", tags = "Recommendation", description = "Endpoints para o documento de Recomendação sobre o Usuario")
@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
	
	@Autowired
	private RecommendationService service;
	
	@ApiOperation(value = "Adição de nova recomendação para um usuário")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateRecomendationUserDTO dto) {
		return service.create(dto);		
	}
	
	
	@ApiOperation(value = "Lista todas as recomendações cadastradas")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();		
	}

	
	@ApiOperation(value = "Atualiza status de uma recomendação que um usuário recebeu")
	@PatchMapping
	public ResponseEntity<?> create(@RequestBody UpdateStatusRecomendationUserDTO dto) {
		return service.updateStatus(dto);		
	}
}
