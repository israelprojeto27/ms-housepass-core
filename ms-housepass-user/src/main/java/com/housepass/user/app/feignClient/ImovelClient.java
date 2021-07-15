package com.housepass.user.app.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.housepass.user.app.dtos.CreateUserResumeImovellDTO;

@Component
@FeignClient(name = "ms-housepass-imoveis",  path="/userResume")
public interface ImovelClient {
		
	@PostMapping("/addUserResume")
	String addUserResume(@RequestBody CreateUserResumeImovellDTO dto);

	@DeleteMapping("/deleteUserResume/{userId}")
	String deleteUserResume(@PathVariable String userId);
	

}
