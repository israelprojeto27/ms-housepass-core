package com.housepass.imoveis.app.dtos;

import com.housepass.imoveis.app.enums.TypeUserEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private String id;
	private String username;
	private String userImageUrl;
	private TypeUserEnum typeUser;	
	
}
