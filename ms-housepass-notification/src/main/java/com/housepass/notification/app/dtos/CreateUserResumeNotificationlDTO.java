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
public class CreateUserResumeNotificationlDTO {
	
	private String userId;
	private String userName;
	private String imageUrl;
	
	public static UserResume toEntity(CreateUserResumeNotificationlDTO dto) {
		return UserResume.builder()
				.userId(dto.getUserId())
				.imageUrl(dto.getImageUrl())
				.userName(dto.getUserName())
				.build();
	}

}
