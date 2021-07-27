package com.housepass.user.app.dtos;

import com.housepass.user.app.enums.NotificationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationDTO {
	
	private String userId;
	private String imovelId;
	private String description;
	private NotificationType type;
	
	private String userSendId;

}
