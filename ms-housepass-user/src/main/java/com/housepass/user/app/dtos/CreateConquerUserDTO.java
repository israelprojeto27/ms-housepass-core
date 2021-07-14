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
public class CreateConquerUserDTO {

	private String userId;
	private String description;
	private String imageUrl;
	
	public static Conquer toEntity(CreateConquerUserDTO dto) {
		return Conquer.builder()
				.description(dto.getDescription())
				.imageUrl(dto.getImageUrl())
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.build();
	}
}
