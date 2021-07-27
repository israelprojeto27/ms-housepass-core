package com.housepass.user.app.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.housepass.user.app.dtos.CreateNotificationDTO;
import com.housepass.user.app.dtos.CreateUserResumeNotificationlDTO;

@Component
@FeignClient(name = "ms-housepass-notification-user-resume",  path="/userResume")
public interface NotificationUserResumeClient {
	
	@PostMapping("/addUserResume")
	String addUserResume(@RequestBody CreateUserResumeNotificationlDTO dto);
	
	
	@DeleteMapping("/deleteUserResume/{userId}")
	String deleteUserResume(@PathVariable String userId);
	
		
	@GetMapping("/findUserResume/{userId}")
	String findUserResume(@PathVariable String userId);
	

	

}
