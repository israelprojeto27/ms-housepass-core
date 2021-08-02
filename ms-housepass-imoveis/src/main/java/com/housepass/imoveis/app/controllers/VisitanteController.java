package com.housepass.imoveis.app.controllers;

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

import com.housepass.imoveis.app.dtos.CreateVisitanteDTO;
import com.housepass.imoveis.app.services.VisitanteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "VisitanteController", tags = "Visitante", description = "Endpoints sobre visitantes de um imóvel")
@RestController
@RequestMapping("/visitante")
public class VisitanteController {

	@Autowired
	private VisitanteService service;
	
	
	@ApiOperation(value = "Cadastro de um novo visitante sobre um imovel")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateVisitanteDTO dto){
		return service.create(dto);		
	}
	
	@ApiOperation(value = "Buscar todos os visitantes cadastrados")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Filtrar os visitantes cadastrados")
	@PostMapping("/filter")
	public ResponseEntity<?> findByFilter(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilter(page, size);
	}
	
	
	@ApiOperation(value = "Buscar visitante por Id")
	@GetMapping("/{visitanteId}")
	public ResponseEntity<?> findById(@PathVariable String visitanteId) {
		return service.findById(visitanteId);
	}
	
	
	@ApiOperation(value = "Buscar visitantes por Imovel Id")
	@GetMapping("/findByImovel/{imovelId}")
	public ResponseEntity<?> findByImovelId(@PathVariable String imovelId) {
		return service.findByImovelId(imovelId);
	}
	
	@ApiOperation(value = "Paginar visitantes por Imovel Id")
	@GetMapping("/filterByImovel/{imovelId}")
	public ResponseEntity<?> findByFilterByImovelId(@PathVariable String imovelId, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilterByImovelId(imovelId, page, size);
	}
	
	@ApiOperation(value = "Deletar visitante por Id")
	@DeleteMapping("/{visitanteId}")
	public ResponseEntity<?> delete(@PathVariable String visitanteId) {
		return service.delete(visitanteId);
	}
}
