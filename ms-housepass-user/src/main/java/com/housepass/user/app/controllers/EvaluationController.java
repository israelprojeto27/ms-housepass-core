package com.housepass.user.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.user.app.dtos.CreateEvaluationUserDTO;
import com.housepass.user.app.services.EvaluationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "EvaluationController", tags = "Evaluation", description = "Endpoints para o documento de Avaliação sobre o Usuario")
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
	
	@Autowired
	private EvaluationService service;
	
	@ApiOperation(value = "Adição de nova avaliação para um usuário")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateEvaluationUserDTO dto) {
		return service.create(dto);		
	}
	
	@ApiOperation(value = "Lista todas as avaliações cadastradas")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();		
	}
	
	

}
