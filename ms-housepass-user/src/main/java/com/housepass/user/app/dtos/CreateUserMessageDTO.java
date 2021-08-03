package com.housepass.user.app.dtos;

import com.housepass.user.app.entities.UserResume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserMessageDTO {

	private String userId;
	private String userName;
	private String userImageUrl;
	
	public static CreateUserMessageDTO fromEntity(UserResume userResume) {
		return CreateUserMessageDTO.builder()
								   .userId(userResume.getUserId())
								   .userName(userResume.getUserName())
								   .userImageUrl(userResume.getImageUrl())
								   .build();
	}
}

 