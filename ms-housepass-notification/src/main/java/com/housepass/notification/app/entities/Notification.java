package com.housepass.notification.app.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document
public class Notification {
	
	@Id
	private String id;
	
	private String imovelId;
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private NotificationType type;
	private StatusEnum status;
	
	@DBRef
	private UserResume user;
	
	@DBRef
	private UserResume userSend;
	
}
