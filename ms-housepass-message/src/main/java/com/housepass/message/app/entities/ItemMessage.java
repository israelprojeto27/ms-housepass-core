package com.housepass.message.app.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ItemMessage {

	@Id
	private String id;
	
	private String messageId;
	private String detailMessage;
	private LocalDateTime createdDate;
	
	@DBRef
	private UserResume userRsumeSend;
}
