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
public class CreateUserResumeNotificationlDTO {
	
	private String userId;
	private String userName;
	private String imageUrl;
	
	public static CreateUserResumeNotificationlDTO fromEntity( UserResume userResume) {
		return CreateUserResumeNotificationlDTO.builder()
				.userId(userResume.getUserId())
				.userName(userResume.getUserName())
				.imageUrl(userResume.getImageUrl())
				.build();
	}

}
