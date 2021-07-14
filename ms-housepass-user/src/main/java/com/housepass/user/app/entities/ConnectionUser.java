package com.housepass.user.app.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.housepass.user.app.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionUser {

	@Id
	private String id;	
	
	private StatusEnum statusConnection;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private String userImageUrl;
	
	@DBRef
	private UserResume userConnection; 
	
	public static ConnectionUser fromEntity(UserResume user) {
		return ConnectionUser.builder()
							 .statusConnection(StatusEnum.ACCEPTED)
							 .createdDate(LocalDateTime.now())
							 .updatedDate(LocalDateTime.now())
							 .userConnection(user)
							 .userImageUrl(user.getImageUrl())
							 .build();
	}
	
}
