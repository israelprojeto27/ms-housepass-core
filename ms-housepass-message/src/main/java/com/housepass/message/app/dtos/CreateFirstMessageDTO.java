package com.housepass.message.app.dtos;


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
	
}
