package com.housepass.message.app.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.housepass.message.app.entities.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
	
	private String id;
	
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private String lastMessage;
	
	private UserResumeDTO userResume;
	
	private List<ItemMessageDTO> itemMessages;
	
	public static MessageDTO fromEntity(Message message) {
		return MessageDTO.builder()
						 .id(message.getId())
						 .lastMessage(message.getLastMessage())
						 .createdDate(message.getCreatedDate())
						 .updatedDate(message.getUpdatedDate())
						 .userResume(UserResumeDTO.fromEntity(message.getUserResumeLastMessage()))
						 .itemMessages(message.getItemMessages() != null ? message.getItemMessages().stream().map(ItemMessageDTO::fromEntity).collect(Collectors.toList()) : null)
						 .build();
	}

}
