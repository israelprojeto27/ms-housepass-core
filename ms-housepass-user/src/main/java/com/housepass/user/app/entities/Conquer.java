package com.housepass.user.app.entities;

import java.time.LocalDateTime;

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
public class Conquer {

	@Id
	private String id;
	
	private String description;
	private String imageUrl;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	
}
