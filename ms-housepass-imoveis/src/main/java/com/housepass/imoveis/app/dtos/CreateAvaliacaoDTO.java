package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.entities.Avaliacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAvaliacaoDTO {
	
	private String imovelId;
	private String userId;
	private Integer rate; 		 
	private String obs;
	
	public static Avaliacao toEntity(CreateAvaliacaoDTO dto) {
		return Avaliacao.builder()
				.imovelId(dto.getImovelId())
				.rate(dto.getRate())
				.obs(dto.getObs())
				.createdDate(LocalDateTime.now())
				.build();
	}

}
