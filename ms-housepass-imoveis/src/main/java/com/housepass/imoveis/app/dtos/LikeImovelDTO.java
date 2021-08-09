package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.entities.LikeImovel;
import com.housepass.imoveis.app.entities.UserResume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeImovelDTO {
	
	private LocalDateTime createdDate;
	private UserResume userResume;
	
	public static LikeImovelDTO fromEntity(LikeImovel likeImovel) {
		return LikeImovelDTO.builder()
							.createdDate(likeImovel.getCreatedDate())
							.userResume(likeImovel.getUserResume())
							.build();
	}
}
