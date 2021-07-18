package com.housepass.imoveis.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.imoveis.app.dtos.CreateOfertaDTO;
import com.housepass.imoveis.app.services.OfertaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "OfertaController", tags = "Oferta", description = "Endpoints para o documento de Oferta para um Imóvel")
@RestController
@RequestMapping("/oferta")
public class OfertaController {
	
	@Autowired
	private OfertaService service;
	
	@ApiOperation(value = "Adiciona uma nova oferta para um imovel")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateOfertaDTO dto) {
		return service.create(dto);		
	}
	
	
	@ApiOperation(value = "Buscar todass as ofertas cadastradas")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();
	}
	
	
	@ApiOperation(value = "Buscar oferta por Id")
	@GetMapping("/{ofertaId}")
	public ResponseEntity<?> findById(@PathVariable String ofertaId) {
		return service.findById(ofertaId);
	}
	
	
	@ApiOperation(value = "Deletar oferta por Id")
	@DeleteMapping("/{ofertaId}")
	public ResponseEntity<?> delete(@PathVariable String ofertaId) {
		return service.delete(ofertaId);
	}
	

}
