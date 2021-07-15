package com.housepass.imoveis.app.dtos;


import com.housepass.imoveis.app.entities.UserResume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResumeImovellDTO {

	private String userId;
	private String userName;
	private String imageUrl;
	
	public static UserResume toEntity(CreateUserResumeImovellDTO dto) {
		return UserResume.builder()
				.userId(dto.getUserId())
				.userName(dto.getUserName())
				.imageUrl(dto.getImageUrl())
				.build();
	}
}
