package com.housepass.user.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.user.app.dtos.ChangePasswordUserDTO;
import com.housepass.user.app.dtos.CreateUserDTO;
import com.housepass.user.app.dtos.ImovelUserDTO;
import com.housepass.user.app.dtos.UpdateImovelUserDTO;
import com.housepass.user.app.dtos.UpdateQuantImovelUserDTO;
import com.housepass.user.app.dtos.UpdateUserDTO;
import com.housepass.user.app.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserController", tags = "User", description = "Endpoints para o documento de User")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
 
	@ApiOperation(value = "Buscar usuarios por diversos parametros")
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestParam(value = "page") int page, 
									@RequestParam(value = "size") int size,
									@RequestParam(value = "value", required = false) String value,
									@RequestParam(value = "type", required = false) String type) {
		
		return service.searchUsers(value, type, page, size);
	}
	
	
	@ApiOperation(value = "Criação de novo usuário")
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateUserDTO dto) {
		return service.create(dto);
	}
	
	
	@ApiOperation(value = "Buscar um usuario por Id")
	@GetMapping("/{userId}")
	public ResponseEntity<?> findById(@PathVariable String  userId) {
		 return service.findById(userId);
	}	
	
	
	@ApiOperation(value = "Buscar um usuario por Id")
	@GetMapping("/ownerImovel/{userId}")
	public ResponseEntity<?> findByIdOwner(@PathVariable String  userId) {
		 return service.findByIdOwnerImovel(userId);
	}
	
	
	@ApiOperation(value = "Listar todos os usuarios")
	@GetMapping
	public ResponseEntity<?> findAll() {
		 return service.findAll();
	}
	
	@ApiOperation(value = "Filtrar os visitantes cadastrados")
	@PostMapping("/filter")
	public ResponseEntity<?> findByFilter(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilter(page, size);
	}
	
	
	@ApiOperation(value = "Buscar um usuário por Login e password")
	@GetMapping("/login")
	public ResponseEntity<?> submitLogin(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password) {
		 return new ResponseEntity<>(service.findByEmailAndPassword(login, password), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Atualizar informações do usuario")
	@PatchMapping("/{userId}")
	public ResponseEntity<?> update(@PathVariable String  userId,
									@RequestBody UpdateUserDTO dto) {
		
		return service.updateUser(userId, dto);
	}
	
	@ApiOperation(value = "Alterar a senha do usuario")
	@PatchMapping("/changePassword/{userId}")
	public ResponseEntity<?> changePassword(@PathVariable String  userId,
									  	    @RequestBody ChangePasswordUserDTO dto) {
		
		return service.changePassword(userId, dto);
	}
	
	@ApiOperation(value = "Adicionar imovel à lista de imoveis do usuario")
	@PostMapping("/addImovel/{userId}")
	public @ResponseBody String addImovelUser(@PathVariable String  userId,
									  	   @RequestBody ImovelUserDTO dto) {		
		return service.addImovel(userId, dto);
	}
	
	
	@ApiOperation(value = "Atualizar informações do imovel da lista de imoveis do usuario")
	@PostMapping("/updateImovel/{userId}/{imovelId}")
	public @ResponseBody String updateImovelUser(@PathVariable String  userId,
											     @PathVariable String  imovelId,
									  	         @RequestBody UpdateImovelUserDTO dto) {		
		return service.updateImovelUser(userId, imovelId, dto);
	}
	
	@ApiOperation(value = "Atualizar informações de quantidade de campos de um imovel da lista de imoveis do usuario")
	@PostMapping("/updateQuantImovel/{userId}/{imovelId}")
	public @ResponseBody String updateQuantImovel(@PathVariable String  userId,
											      @PathVariable String  imovelId,
									  	          @RequestBody UpdateQuantImovelUserDTO dto) {		
		
		return service.updateQuantImovel(userId, imovelId, dto);
	}
	
	
	@ApiOperation(value = "Checa se exist um imovel a partir do imovelId e userId")
	@GetMapping("/findImovelByUserByImovel/{userId}/{imovelId}")
	public @ResponseBody String findImovelByImovelIdByUserId(@PathVariable String  userId,
											  			     @PathVariable String  imovelId) {
		
		return service.findImovelByImovelIdByUserId(userId, imovelId);
	}
	
	
	@ApiOperation(value = "Delete usuario por Id")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> delete(@PathVariable String  userId) {		
		return service.delete(userId);
	}
	

}
