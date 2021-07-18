package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.entities.Recomendacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecomendacaoDTO {

	private String imovelId;	
	private String descricao;
	private String userId;
	
	public static Recomendacao toEntity(CreateRecomendacaoDTO dto) {
		return Recomendacao.builder()
				.imovelId(dto.getImovelId())
				.descricao(dto.getDescricao())				
				.createdDate(LocalDateTime.now())
				.build();
	}
	
}
