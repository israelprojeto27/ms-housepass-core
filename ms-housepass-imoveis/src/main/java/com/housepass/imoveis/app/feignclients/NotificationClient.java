package com.housepass.imoveis.app.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.housepass.imoveis.app.dtos.CreateNotificationDTO;


@Component
@FeignClient(name = "ms-housepass-notification",  path="/notification")
public interface NotificationClient {

	@PostMapping("/addNotification")
	String addNotification(@RequestBody CreateNotificationDTO dto);
}
