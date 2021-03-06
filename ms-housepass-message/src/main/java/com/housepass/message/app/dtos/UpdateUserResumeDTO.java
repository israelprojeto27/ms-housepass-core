package com.housepass.message.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserResumeDTO {
	
	private String userId;
	private String userName;
	private String userImageUrl;
}
