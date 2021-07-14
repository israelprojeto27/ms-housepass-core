package com.housepass.user.app.dtos;

import java.time.LocalDateTime;

import com.housepass.user.app.entities.Recommendation;
import com.housepass.user.app.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecomendationUserDTO {
	
	private String userReceiveRecommendationId; // usuario que recebeu a recomendacao	
	private String description;	
	private String userSendRecommendationId; //Id do susuaio que enviou a recomendacao
	
	
	public static Recommendation toEntity(CreateRecomendationUserDTO dto) {
		return Recommendation.builder()
				.userReceiveRecommendationId(dto.getUserReceiveRecommendationId())
				.description(dto.getDescription())				
				.createdDate(LocalDateTime.now())
				.statusEvaluation(StatusEnum.SENT)
				.build();
	}

}
