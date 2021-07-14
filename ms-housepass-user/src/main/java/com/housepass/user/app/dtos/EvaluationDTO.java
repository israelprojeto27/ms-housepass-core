package com.housepass.user.app.dtos;

import java.time.LocalDateTime;

import com.housepass.user.app.entities.Evaluation;
import com.housepass.user.app.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {
	
	private String id;
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private String username;
	private String userimageUrl;
	private UserResumeDTO userSendEvaluation;
	
	public static EvaluationDTO fromEntity(Evaluation evaluation) {
		return EvaluationDTO.builder()
				.id(evaluation.getId())
				.description(evaluation.getDescription())
				.createdDate(evaluation.getCreatedDate())
				.updatedDate(evaluation.getUpdatedDate())
				.username(evaluation.getUserEvaluator().getUserName())
				.userimageUrl(evaluation.getUserEvaluator().getImageUrl())				
				.userSendEvaluation(UserResumeDTO.fromEntity(evaluation.getUserEvaluator()))
				.build();
	}

}
