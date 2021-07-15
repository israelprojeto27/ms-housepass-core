package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.entities.Oferta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfertaDTO {
	
	private String imovelId;
	private String userId;
	private Double valor;	
	private String obs;
	
	public static Oferta toEntity(CreateOfertaDTO dto) {
		return Oferta.builder()
				.imovelId(dto.getImovelId())
				.obs(dto.getObs())
				.valor(dto.getValor())
				.createdDate(LocalDateTime.now())
				.build();
	}

}
