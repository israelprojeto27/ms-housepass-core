package com.housepass.user.app.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
public class Evaluation {
	
	@Id
	private String id;
	
	private String userReceiveEvaluationId; // usuario que recebeu a avaliação
	private int points; // deve variar de 1 a 5
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;	
	
	@DBRef
	private UserResume userEvaluator; // usuario que enviou a avaliação

}
