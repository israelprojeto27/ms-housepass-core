package com.housepass.message.app.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
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
public class Message {
	
	@Id
	private String id;
	
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	private List<ItemMessage> itemMessages;
	
	

}
