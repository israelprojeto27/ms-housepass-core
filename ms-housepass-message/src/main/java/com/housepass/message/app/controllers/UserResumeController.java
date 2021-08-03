package com.housepass.message.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.message.app.dtos.CreateUserResumeDTO;
import com.housepass.message.app.dtos.UpdateUserResumeDTO;
import com.housepass.message.app.services.UserResumeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserResumeController", tags = "UserResume", description = "Endpoints para o documento de UserResume")
@RestController
@RequestMapping("/userResume")
public class UserResumeController {
	
	@Autowired
	private UserResumeService service;
	
	
	@ApiOperation(value = "Adicionar novo Usuario ao MS de Message")
	@PostMapping("/addUserResume")
	public @ResponseBody String addUserResume(@RequestBody CreateUserResumeDTO dto) {
		return service.addUserResume(dto);
	}
	
	
	@ApiOperation(value = "Atualizar informações do Usuario no MS de Message")
	@PatchMapping("/updateUserResume")
	public @ResponseBody String updateUserResume(@RequestBody UpdateUserResumeDTO dto) {
		return service.updateUserResume(dto);
	}
	
	
	@ApiOperation(value = "Deletar Usuario no MS de Message")
	@DeleteMapping("/deleteUserResume/{userId}")
	public @ResponseBody String deleteUserResume(@PathVariable String userId) {
		return service.deleteUserResume(userId);
	}
	
	@ApiOperation(value = "Listar todos os User resume criados")
	@GetMapping
	public ResponseEntity<?> findAll() {
		 return service.findAll();
	}

}
