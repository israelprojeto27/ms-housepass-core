package com.housepass.user.app.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserResume {
	
	@Id
	private String id;
	
	private String userId;
	private String userName;
	private String imageUrl;
	
	public static UserResume fromEntity(User user) {
		return UserResume.builder()
				.userId(user.getId())
				.userName(user.getName())
				.imageUrl(user.getImageUserUrl())
				.build();
	}

}
