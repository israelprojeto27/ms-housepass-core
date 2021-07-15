package com.housepass.imoveis.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.imoveis.app.dtos.CreateUserResumeImovellDTO;
import com.housepass.imoveis.app.services.UserResumeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserResumeController", tags = "UserResume", description = "REST Api de MS User Resume")
@RestController
@RequestMapping("/userResume")
public class UserResumeController {
	
	@Autowired
	private UserResumeService service;	

	
	@ApiOperation(value = "Adicionar um novo User Resume")
	@PostMapping("/addUserResume")
	public @ResponseBody String addUserResume(@RequestBody CreateUserResumeImovellDTO dto){
		 return service.add(dto);		 
	}

	
	@DeleteMapping("/deleteUserResume/{userId}")
	public @ResponseBody String deleteUserResume(@PathVariable String userId) {
		return service.delete(userId);	
	}
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return service.findAll();	
	}
}
