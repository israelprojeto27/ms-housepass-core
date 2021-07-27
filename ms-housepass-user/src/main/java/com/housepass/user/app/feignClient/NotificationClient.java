package com.housepass.user.app.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.housepass.user.app.dtos.CreateNotificationDTO;


@Component
@FeignClient(name = "ms-housepass-notification",  path="/notification")
public interface NotificationClient {
	
	@PostMapping
	String addNotification(@RequestBody CreateNotificationDTO dto);

}
