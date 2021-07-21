package com.housepass.user.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.user.app.dtos.CreateUserDTO;
import com.housepass.user.app.dtos.UpdateConfigurationDTO;
import com.housepass.user.app.services.ConfigurationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ConfigurationController", tags = "Configuration", description = "Endpoints para o documento de configuration")
@RestController
@RequestMapping("/configuration")
public class ConfigurationController {
	
	@Autowired
	private ConfigurationService service;
	
	
	// Esse endpoint poderá ser chamado quando um novo usuario
	@ApiOperation(value = "Criação de uma configuração")
	@PostMapping("/{userId}")
	public ResponseEntity<?> create(@PathVariable String  userId) {
		return service.create(userId);
	}
	
	@ApiOperation(value = "Atualiza configurações de um usuario")
	@PatchMapping("/{userId}")
	public ResponseEntity<?> create(@PathVariable String  userId, @RequestBody UpdateConfigurationDTO dto) {
		return service.update(userId, dto);
	}
	

}
