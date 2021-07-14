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
public class RecommendationDTO {
	
	private String id;
	private String description;
	private LocalDateTime dateRecommendation;
	private StatusEnum statusEvaluation;
	private UserResumeDTO userSendRecommendation;	
		
		
	public static RecommendationDTO fromEntity(Recommendation recommendation) {
		return RecommendationDTO.builder()
				.id(recommendation.getId())
				.description(recommendation.getDescription())
				.dateRecommendation(recommendation.getCreatedDate())
				.statusEvaluation(recommendation.getStatusEvaluation())
				.userSendRecommendation(UserResumeDTO.fromEntity(recommendation.getUserSendRecommendation()))				
				.build();
	}
}
