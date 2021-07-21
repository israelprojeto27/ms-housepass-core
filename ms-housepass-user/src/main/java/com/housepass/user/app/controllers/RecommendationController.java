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
	
	@ApiOperation(value = "Filtrar as recomendações cadastradas")
	@PostMapping("/filter")
	public ResponseEntity<?> findByFilter(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilter(page, size);
	}

	
	@ApiOperation(value = "Atualiza status de uma recomendação que um usuário recebeu")
	@PatchMapping
	public ResponseEntity<?> create(@RequestBody UpdateStatusRecomendationUserDTO dto) {
		return service.updateStatus(dto);		
	}
	
	@ApiOperation(value = "Deleta uma recomendação por Id")
	@DeleteMapping("/{recommendationId}")
	public ResponseEntity<?> delete(@PathVariable String recommendationId) {
		return service.deleteById(recommendationId);		
	}
	
	@ApiOperation(value = "Buscar recomendações por User Id")
	@GetMapping("/findByUser/{userId}")
	public ResponseEntity<?> findByUserId(@PathVariable String userId) {
		return service.findByUserId(userId);
	}
	
	@ApiOperation(value = "Paginar conquistas por User Id")
	@GetMapping("/filterByUser/{userId}")
	public ResponseEntity<?> findByFilterByUserId(@PathVariable String userId, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilterByUserId(userId, page, size);
	}

}
