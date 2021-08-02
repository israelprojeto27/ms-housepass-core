package com.housepass.message.app.dtos;


import java.time.LocalDateTime;

import com.housepass.message.app.entities.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFirstMessageDTO {

	private String detailMessage;
	private String userResumeSendId;
	private String userResumeReceiveId;
	
	
	public static Message toEntity(CreateFirstMessageDTO dto) {		
		return Message.builder()
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.lastMessage(dto.getDetailMessage())
				.build();
	}
	
}
