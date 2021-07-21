package com.housepass.user.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.user.app.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ImovelUserController", tags = "ImovelUser", description = "Endpoints para recuperar informações de Imoveis relacionado a usuarios")
@RestController
@RequestMapping("/imovelUser")
public class ImovelUserController {

	
	@Autowired
	private UserService service;
	

	@ApiOperation(value = "Buscar imoveis por User Id")
	@GetMapping("/{userId}")
	public ResponseEntity<?> findImoveisByUserId(@PathVariable String  userId) {
		 return service.findImoveisByUserId(userId);
	}	
	
	@ApiOperation(value = "Paginar imoveis por User Id")
	@GetMapping("/filterByUser/{userId}") 
	public ResponseEntity<?> filterImoveisByUserId(@PathVariable String userId, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.filterImoveisByUserId(userId, page, size);
	}
}
