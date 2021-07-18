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
public class CreateVisitanteDTO {
	
	private String imovelId;
	private String userId;
	
	
	public static Visitante toEntity(CreateVisitanteDTO dto) {
		return Visitante.builder()
				.imovelId(dto.getImovelId())							
				.createdDate(LocalDateTime.now())
				.build();
	}

}
