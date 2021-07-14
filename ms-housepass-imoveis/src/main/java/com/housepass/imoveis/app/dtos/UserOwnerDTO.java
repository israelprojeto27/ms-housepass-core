package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.entities.User;
import com.housepass.imoveis.app.enums.TypeUserEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOwnerDTO {

	private String userId;
	private String username;
	private String userImageUrl;
	private TypeUserEnum userType;
	
	public static User toEntity(UserOwnerDTO dto) {
		return User.builder()
				.userId(dto.getUserId())
				.username(dto.getUsername())
				.userImageUrl(dto.getUserImageUrl())
				.typeUser(dto.getUserType())
				.lastChangeInfoUser(LocalDateTime.now())				
				.build();
	}
	
}
