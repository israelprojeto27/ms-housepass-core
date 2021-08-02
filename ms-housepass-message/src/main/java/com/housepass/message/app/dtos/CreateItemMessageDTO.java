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
public class CreateItemMessageDTO {
	
	private String messageId;
	private String detailMessage;
	private String userSendResumeId;
	
	public static ItemMessage toEntity(CreateItemMessageDTO dto) {

		return ItemMessage.builder()
				.createdDate(LocalDateTime.now())
				.detailMessage(dto.getDetailMessage())
				.messageId(dto.getMessageId())				
				.build();
	}

}
