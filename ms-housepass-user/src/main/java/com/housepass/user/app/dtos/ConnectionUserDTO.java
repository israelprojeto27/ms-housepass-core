package com.housepass.user.app.dtos;

import com.housepass.user.app.entities.ConnectionUser;
import com.housepass.user.app.enums.TypeUserEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ConnectionUserDTO {

	private String userId;
	private String name;	
	private String userImageUrl;
	
	public static ConnectionUserDTO fromEntity(ConnectionUser connection) {
		return ConnectionUserDTO.builder()
				.userId(connection.getUserConnection().getId())
				.name(connection.getUserConnection().getUserName())	
				.userImageUrl(connection.getUserImageUrl())
				.build();
						
	}
}
