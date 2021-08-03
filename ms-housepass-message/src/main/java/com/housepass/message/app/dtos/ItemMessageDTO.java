package com.housepass.message.app.dtos;

import java.time.LocalDateTime;

import com.housepass.message.app.entities.ItemMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemMessageDTO {
	
	private String id;
	
	private String messageId;
	private String detailMessage;
	private LocalDateTime createdDate;
	private UserResumeDTO userResumeSend;
	
	public static ItemMessageDTO fromEntity(ItemMessage itemMessage) {
		return ItemMessageDTO.builder()
				.id(itemMessage.getId())
				.messageId(itemMessage.getMessageId())
				.detailMessage(itemMessage.getDetailMessage())
				.createdDate(itemMessage.getCreatedDate())
				.userResumeSend(UserResumeDTO.fromEntity(itemMessage.getUserResumeSend()))
				.build();
	}

}
