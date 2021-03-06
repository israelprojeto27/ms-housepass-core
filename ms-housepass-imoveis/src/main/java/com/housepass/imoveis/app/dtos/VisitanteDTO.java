package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.entities.Visitante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitanteDTO {

	private String id;
	private String userId;
	private String username;
	private String userImageUrl;
	private LocalDateTime createdDate;
	
	public static VisitanteDTO fromEntity(Visitante visitante) {
		return VisitanteDTO.builder()
				.id(visitante.getId())
				.userId(visitante.getUserVisitante().getUserId())
				.username(visitante.getUserVisitante().getUserName())
				.userImageUrl(visitante.getUserVisitante().getImageUrl())				
				.createdDate(visitante.getCreatedDate())
				.build();
	}
}
