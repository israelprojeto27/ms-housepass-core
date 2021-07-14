package com.housepass.user.app.dtos;

import com.housepass.user.app.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
	
	private String name;
	private String email;	
	private String location;
	private String phone;
	private String webSite;
	private String about;
	
	public static User toEntity(UpdateUserDTO dto) {
		return User.builder()
				.name(dto.getName())
				.email(dto.getEmail())
				.location(dto.getLocation())
				.phone(dto.getPhone())
				.webSite(dto.getWebSite())
				.about(dto.getAbout())
				.build();
	}

}
