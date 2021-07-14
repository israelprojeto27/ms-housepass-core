package com.housepass.user.app.dtos;

import com.housepass.user.app.entities.UserResume;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResumeDTO {
	
	private String userId;
	private String userName;
	private String userImageUrl;

	public static UserResumeDTO fromEntity(UserResume userResume) {
		return UserResumeDTO.builder()
				.userId(userResume.getUserId())
				.userName(userResume.getUserName())
				.userImageUrl(userResume.getImageUrl())
				.build();
	}
}
