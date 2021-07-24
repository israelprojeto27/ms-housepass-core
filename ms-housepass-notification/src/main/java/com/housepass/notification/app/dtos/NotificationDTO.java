package com.housepass.notification.app.dtos;

import java.time.LocalDateTime;

import com.housepass.notification.app.entities.Notification;
import com.housepass.notification.app.enums.NotificationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
	
	private String id;
	private String description;
	private NotificationType type;
	private LocalDateTime createdDate;
	
	private UserResumeDTO userResume;
	private UserResumeDTO userResumeSend;
	private String imovelId;
	
	
	public static NotificationDTO fromEntity(Notification notification) {
		return NotificationDTO.builder()
				.id(notification.getId())
				.description(notification.getDescription())
				.type(notification.getType())
				.createdDate(notification.getCreatedDate())
				.userResume(UserResumeDTO.fromEntity(notification.getUser()))
				.userResumeSend(UserResumeDTO.fromEntity(notification.getUserSend()))
				.imovelId(notification.getImovelId())
				.build();
	}

}
