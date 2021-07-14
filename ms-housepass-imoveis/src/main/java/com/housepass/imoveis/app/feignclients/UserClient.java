package com.housepass.imoveis.app.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.housepass.imoveis.app.dtos.ImovelUserDTO;
import com.housepass.imoveis.app.dtos.UpdateImovelDTO;
import com.housepass.imoveis.app.dtos.UserOwnerDTO;

@Component
@FeignClient(name = "ms-housepass-user",  path="/user")
public interface UserClient {
	
	@GetMapping("/ownerImovel/{userId}")
	ResponseEntity<UserOwnerDTO> findByIdOwner(@PathVariable String  userId);
	
	@PostMapping("/addImovel/{userId}")
	ResponseEntity<?> addImovelUser(@PathVariable String  userId,
									@RequestBody ImovelUserDTO dto);
	
	
	@PostMapping("/updateImovel/{userId}/{imovelId}")
	ResponseEntity<?> updateImovelUser(@PathVariable String  userId,
									   @PathVariable String  imovelId,
							  	       @RequestBody UpdateImovelDTO dto);	
	

}
