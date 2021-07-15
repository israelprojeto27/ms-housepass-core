package com.housepass.imoveis.app.dtos;

import com.housepass.imoveis.app.entities.UserResume;
import com.housepass.imoveis.app.enums.TypeUserEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResumeDTO {

	private String userId;
	private String username;
	private String userImageUrl;	
	
	public static UserResumeDTO fromEntity(UserResume userResume) {
		return UserResumeDTO.builder()
							.userId(userResume.getUserId())
							.username(userResume.getUserName())
							.userImageUrl(userResume.getImageUrl())
							.build();
	}
	
}
