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
	private LocalDateTime dataVisita;
	
	public static VisitanteDTO fromEntity(Visitante visitante) {
		return VisitanteDTO.builder()
				.id(visitante.getId())
				.userId(visitante.getUserVisitante().getUserId())
				.username(visitante.getUserVisitante().getUsername())
				.userImageUrl(visitante.getUserVisitante().getUserImageUrl())				
				.dataVisita(visitante.getDataVisita())
				.build();
	}
}
