package com.housepass.user.app.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housepass.user.app.dtos.CreateUserMessageDTO;


@Component
@FeignClient(name = "ms-housepass-message")
public interface MessageClient {
	
	@PostMapping("/userResume/addUserResume")
	public @ResponseBody String addUserResume(CreateUserMessageDTO dto);

}
