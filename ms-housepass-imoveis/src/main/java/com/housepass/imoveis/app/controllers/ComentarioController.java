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

import com.housepass.imoveis.app.dtos.CreateComentarioDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ComentarioController", tags = "Comentario", description = "Endpoints sobre comentarios de um imóvel")
@RestController
@RequestMapping("/comentario")
public class ComentarioController {
	
	@Autowired
	private ComentarioService service;
	
	@ApiOperation(value = "Cadastro de um novo comentario sobre um imovel")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateComentarioDTO dto){
		return service.create(dto);		
	}
	
	@ApiOperation(value = "Buscar todos os comentarios cadastrados")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();
	}
	
	
	@ApiOperation(value = "Buscar comentario por Id")
	@GetMapping("/{comentarioId}")
	public ResponseEntity<?> findById(@PathVariable String comentarioId) {
		return service.findById(comentarioId);
	}
	
	
	@ApiOperation(value = "Deletar comentario por Id")
	@DeleteMapping("/{comentarioId}")
	public ResponseEntity<?> delete(@PathVariable String comentarioId) {
		return service.delete(comentarioId);
	}
	

}
