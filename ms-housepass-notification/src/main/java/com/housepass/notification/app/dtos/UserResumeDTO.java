package com.housepass.notification.app.dtos;

import com.housepass.notification.app.entities.UserResume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
