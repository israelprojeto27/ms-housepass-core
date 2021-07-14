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
public class Recommendation {

	@Id
	private String id;	
	
	private String userReceiveRecommendationId; // usuario que recebeu a recomendação
	
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private StatusEnum statusEvaluation;	
	
	@DBRef
	private UserResume userSendRecommendation; // // usuario que enviou a recomendação
	
}
