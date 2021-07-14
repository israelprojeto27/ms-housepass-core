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
public class AvaliacaoDTO {

	private String id;
	private String userId;
	private String username;
	private String userImageUrl;
	private String descricao;
	private Integer rate; 
	private LocalDateTime dataAvaliacao;
	
	public static AvaliacaoDTO fromEntity(Avaliacao avaliacao) {
		return AvaliacaoDTO.builder()
				.id(avaliacao.getId())
				.userId(avaliacao.getUserAvaliacao().getUserId())
				.username(avaliacao.getUserAvaliacao().getUsername())
				.userImageUrl(avaliacao.getUserAvaliacao().getUserImageUrl())
				.descricao(avaliacao.getDescricao())
				.dataAvaliacao(avaliacao.getData())
				.rate(avaliacao.getRate())
				.build();
	}
}
