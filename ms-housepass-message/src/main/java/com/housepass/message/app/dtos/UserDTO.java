package com.housepass.message.app.dtos;

import com.housepass.message.app.entities.UserResume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String userId;
	private String userName;
	private String userImageUrl;
	
	public static UserDTO fromEntity(UserResume userResume) {
		return UserDTO.builder()
				.userId(userResume.getUserId())
				.userName(userResume.getUserName())
				.userImageUrl(userResume.getUserImageUrl())
				.build();
	}
}
