package com.housepass.user.app.dtos;

import com.housepass.user.app.entities.User;
import com.housepass.user.app.enums.TypeUserEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOwnerImovelDTO {
	
	private String userId;
	private String username;
	private String userImageUrl;
	private TypeUserEnum userType;
	
	public static UserOwnerImovelDTO fromEntity(User user) {
		return UserOwnerImovelDTO.builder()
				.userId(user.getId())
				.username(user.getName())
				.userImageUrl(user.getImageUserUrl())
				.userType(user.getTypeUser())
				.build();
	}

}
