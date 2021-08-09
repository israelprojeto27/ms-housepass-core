package com.housepass.imoveis.app.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document
public class LikeImovel {
	
	@Id
	private String id;
	
	private String userId;
	private String imovelId;
	private LocalDateTime createdDate;
	private UserResume userResume;
	
	
	public LikeImovel(String userId, String imovelId, UserResume userResume) {
		this.userId = userId;
		this.userResume = userResume;
		this.imovelId = imovelId;
		this.createdDate = LocalDateTime.now();
	}
}
