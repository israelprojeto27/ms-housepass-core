package com.housepass.user.app.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housepass.user.app.dtos.CreateUserResumeImovellDTO;

import io.swagger.annotations.ApiOperation;

@Component
@FeignClient(name = "ms-housepass-imoveis",  path="/userResume")
public interface ImovelClient {
		
	@PostMapping("/addUserResume")
	String addUserResume(@RequestBody CreateUserResumeImovellDTO dto);

	@DeleteMapping("/deleteUserResume/{userId}")
	String deleteUserResume(@PathVariable String userId);
	
		
	@GetMapping("/findUserResume/{userId}")
	String findUserResume(@PathVariable String userId);
	
	

}
