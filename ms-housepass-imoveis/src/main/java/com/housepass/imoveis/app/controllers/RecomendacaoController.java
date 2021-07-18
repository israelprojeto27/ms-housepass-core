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

import com.housepass.imoveis.app.dtos.CreateRecomendacaoDTO;
import com.housepass.imoveis.app.services.RecomendacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "RecomendacaoController", tags = "Recomendacao", description = "Endpoints sobre recomendações de um imóvel")
@RestController
@RequestMapping("/recomendacao")
public class RecomendacaoController {

	
	@Autowired
	private RecomendacaoService service;
	
	
	@ApiOperation(value = "Cadastro de um nova recomendacao sobre um imovel")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateRecomendacaoDTO dto){
		return service.create(dto);		
	}
	
	
	@ApiOperation(value = "Buscar todas as recomendações cadastradas")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();
	}
	
	
	@ApiOperation(value = "Buscar recomendacao por Id")
	@GetMapping("/{recomendacaoId}")
	public ResponseEntity<?> findById(@PathVariable String recomendacaoId) {
		return service.findById(recomendacaoId);
	}
	
	

	@ApiOperation(value = "Deletar recomendacao por Id")
	@DeleteMapping("/{recomendacaoId}")
	public ResponseEntity<?> delete(@PathVariable String recomendacaoId) {
		return service.delete(recomendacaoId);
	}
	
}
