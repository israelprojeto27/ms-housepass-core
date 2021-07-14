package com.housepass.user.app.dtos;

import com.housepass.user.app.entities.User;
import com.housepass.user.app.enums.TypeUserEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

	private String name;
	private String email;
	private String password;
	private String location;
	private String phone;
	private String webSite;
	private TypeUserEnum typeUser;
	private String about;
	private String imageUserUrl;
	
	public static User toEntity(CreateUserDTO dto) {
		return User.builder()
				.email(dto.getEmail())
				.name(dto.getName())
				.password(dto.getPassword())
				.location(dto.getLocation())
				.phone(dto.getPhone())
				.webSite(dto.getWebSite())
				.typeUser(dto.getTypeUser())
				.about(dto.getAbout())
				.imageUserUrl(dto.getImageUserUrl())
				.quantConnections(0l)
				.quantImoveis(0l)
				.quantPublications(0l)
				.build();
	}
	
	
}
