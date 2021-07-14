package com.housepass.user.app.dtos;

import com.housepass.user.app.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusRecomendationUserDTO {
	
	private String recommendationId;
	private String userReceiveRecommendationId;
	private StatusEnum statusRecommendation;

}
