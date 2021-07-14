package com.housepass.user.app.dtos;

import java.time.LocalDateTime;

import com.housepass.user.app.entities.Conquer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConquerDTO {

	private String id;	
	private String description;
	private LocalDateTime dateConquer;
	private String imageUrl;
	
	public static ConquerDTO fromEntity(Conquer conquer) {
		return ConquerDTO.builder()
				.id(conquer.getId())
				.description(conquer.getDescription())
				.dateConquer(conquer.getCreatedDate())				
				.imageUrl(conquer.getImageUrl())
				.build();
	}
}
