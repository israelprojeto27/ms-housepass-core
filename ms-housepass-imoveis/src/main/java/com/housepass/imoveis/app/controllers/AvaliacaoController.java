package com.housepass.imoveis.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.imoveis.app.dtos.CreateAvaliacaoDTO;
import com.housepass.imoveis.app.services.AvaliacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "AvaliacaoController", tags = "Avaliacao", description = "Endpoints sobre avaliações de um imóvel")
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService service;
	
	
	@ApiOperation(value = "Cadastro de um nova avaliação sobre um imovel")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateAvaliacaoDTO dto){
		return service.create(dto);		
	}
	
	@ApiOperation(value = "Buscar todas as avaliações cadastradas")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();
	}
	
	
	@ApiOperation(value = "Buscar avaliacao por Id")
	@GetMapping("/{avaliacaoId}")
	public ResponseEntity<?> findById(@PathVariable String avaliacaoId) {
		return service.findById(avaliacaoId);
	}
	
	@ApiOperation(value = "Deletar avaliação por Id")
	@DeleteMapping("/{avaliacaoId}")
	public ResponseEntity<?> delete(@PathVariable String avaliacaoId) {
		return service.delete(avaliacaoId);
	}
	

}
