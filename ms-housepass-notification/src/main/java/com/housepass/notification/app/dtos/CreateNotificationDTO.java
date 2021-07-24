package com.housepass.notification.app.dtos;

import java.time.LocalDateTime;

import com.housepass.notification.app.entities.Notification;
import com.housepass.notification.app.enums.NotificationType;
import com.housepass.notification.app.enums.StatusEnum;

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
	
	private String userSendId; // id do usuario que, por exemplo, enviou a oferta ou um invite
	
	public static Notification toEntity(CreateNotificationDTO dto) {
		return Notification.builder()
				.imovelId(dto.getImovelId())
				.description(dto.getDescription())
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.type(dto.getType())
				.status(StatusEnum.SENT)
				.build();
	}

}
