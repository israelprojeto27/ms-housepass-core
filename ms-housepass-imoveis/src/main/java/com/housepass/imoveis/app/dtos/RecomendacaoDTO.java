package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.entities.Avaliacao;
import com.housepass.imoveis.app.entities.Recomendacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecomendacaoDTO {

	private String id;
	private String userId;
	private String username;
	private String userImageUrl;
	private String descricao;	
	private LocalDateTime createdDate;
	
	
	public static RecomendacaoDTO fromEntity(Recomendacao recomendacao) {
		return RecomendacaoDTO.builder()
				.id(recomendacao.getId())
				.userId(recomendacao.getUserRecomendacao().getUserId())
				.username(recomendacao.getUserRecomendacao().getUserName())
				.userImageUrl(recomendacao.getUserRecomendacao().getImageUrl())
				.descricao(recomendacao.getDescricao())
				.createdDate(recomendacao.getCreatedDate())				
				.build();
	}
}
