package com.housepass.user.app.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.housepass.user.app.entities.Evaluation;
import com.housepass.user.app.enums.StatusEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEvaluationUserDTO {
	
	private String userReceiveEvaluationId; // usuario que recebeu a avaliação
	private int points; // deve variar de 1 a 5
	private String description;
	
	private String userEvaluatorId; //Id do susuaio que enviou a avaliação
	
	
	public static Evaluation toEntity(CreateEvaluationUserDTO dto) {
		return Evaluation.builder()
				.userReceiveEvaluationId(dto.getUserReceiveEvaluationId())
				.description(dto.getDescription())
				.points(dto.getPoints())
				.createdDate(LocalDateTime.now())				
				.build();
	}

}
