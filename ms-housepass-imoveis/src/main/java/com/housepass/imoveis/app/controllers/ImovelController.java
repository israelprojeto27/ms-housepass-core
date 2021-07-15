package com.housepass.imoveis.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.imoveis.app.dtos.CreateImovelDTO;
import com.housepass.imoveis.app.dtos.CreateUserResumeImovellDTO;
import com.housepass.imoveis.app.dtos.UpdateImovelDTO;
import com.housepass.imoveis.app.services.ImovelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "ImovelController", tags = "Imovel", description = "REST Api de MS Imovel")
@RestController
@RequestMapping("/imovel")
public class ImovelController {
	
	@Autowired
	private ImovelService service;
 
	@ApiOperation(value = "Cadastro de um novo imóvel")
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody CreateImovelDTO dto){
		return service.create(dto);		
	}
	
	@ApiOperation(value = "Atualiza informações do imóvel")
	@PatchMapping("/{imovelId}")
	public ResponseEntity<?> update(@PathVariable String imovelId,
									@RequestBody UpdateImovelDTO dto){		
		return service.update(imovelId, dto);
	}
	
	
	@ApiOperation(value = "Buscar todos os imoveis")
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return service.findAll();
	}
	
	
	@ApiOperation(value = "Buscar imovel por Id")
	@GetMapping("/{imovelId}")
	public ResponseEntity<?> findById(@PathVariable String imovelId) {
		return service.findById(imovelId);
	}

	

}
