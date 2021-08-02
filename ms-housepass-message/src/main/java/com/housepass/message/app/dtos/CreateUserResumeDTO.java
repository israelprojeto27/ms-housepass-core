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
public class CreateUserResumeDTO {
	
	private String userId;
	private String userName;
	private String userImageUrl;
	
	public static UserResume toEntity(CreateUserResumeDTO dto) {		
		return UserResume.builder()
				.userId(dto.getUserId())
				.userName(dto.getUserName())
				.userImageUrl(dto.getUserImageUrl())
				.build();
	}
	
	

}
