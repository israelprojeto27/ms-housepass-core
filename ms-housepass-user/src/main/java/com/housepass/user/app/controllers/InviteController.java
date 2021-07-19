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

import com.housepass.user.app.dtos.CreateInviteDTO;
import com.housepass.user.app.dtos.UpdateStatusInviterDTO;
import com.housepass.user.app.services.InviteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "InviteController", tags = "Invite", description = "Endpoints para o documento de Convite de um usuario")
@RestController
@RequestMapping("/invite")
public class InviteController {
	
	@Autowired
	private InviteService service;
	
	@ApiOperation(value = "Cria um convite que foi enviado por um usuario")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateInviteDTO dto) {
		return service.create(dto);		
	}
	
	@ApiOperation(value = "Lista os convites cadastrados cadastradas")
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();		
	}
	
	@ApiOperation(value = "Filtrar os convites cadastrados")
	@PostMapping("/filter")
	public ResponseEntity<?> findByFilter(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilter(page, size);
	}

	
	@ApiOperation(value = "Atualiza status de um convite que um usuário recebeu")
	@PatchMapping
	public ResponseEntity<?> create(@RequestBody UpdateStatusInviterDTO dto) {
		return service.updateStatus(dto);		
	}
	
	
	@ApiOperation(value = "Deleta um convite por Id")
	@DeleteMapping("/{inviteId}")
	public ResponseEntity<?> delete(@PathVariable String inviteId) {
		return service.deleteById(inviteId);		
	}

}
