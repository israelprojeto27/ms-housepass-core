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

import com.housepass.imoveis.app.dtos.CreateValorImovelDTO;
import com.housepass.imoveis.app.services.ValorImovelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ValorImovelController", tags = "ValorImovel", description = "Endpoints sobre valores imoveis de um imóvel")
@RestController
@RequestMapping("/valorImovel")
public class ValorImovelController {
	
	@Autowired
	private ValorImovelService service;
	
	@ApiOperation(value = "Cadastro de um novo valor imovel sobre um imovel")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateValorImovelDTO dto){
		return service.create(dto);		
	}
	
	@ApiOperation(value = "Buscar todos os valores imoveis cadastrados")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Filtrar os valores imoveis cadastrados")
	@PostMapping("/filter")
	public ResponseEntity<?> findByFilter(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilter(page, size);
	}
	
	
	@ApiOperation(value = "Buscar visitante por Id")
	@GetMapping("/{valorImovelId}")
	public ResponseEntity<?> findById(@PathVariable String valorImovelId) {
		return service.findById(valorImovelId);
	}
	
	
	@ApiOperation(value = "Deletar valor imovel por Id")
	@DeleteMapping("/{valorImovelId}")
	public ResponseEntity<?> delete(@PathVariable String valorImovelId) {
		return service.delete(valorImovelId);
	}

}
