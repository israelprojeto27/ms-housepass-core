package com.housepass.user.app.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.housepass.user.app.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invite {
	
	@Id
	private String id;
	
	private String userReceiveInviteId;
	private String message;
	private StatusEnum statusInvite;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	@DBRef
	private UserResume userSendInvite; // usuario que enviou o convite

}
