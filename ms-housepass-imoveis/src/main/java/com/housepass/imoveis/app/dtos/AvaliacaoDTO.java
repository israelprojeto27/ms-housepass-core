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
	private String obs;
	private Integer rate; 
	private LocalDateTime createdDate;
	
	public static AvaliacaoDTO fromEntity(Avaliacao avaliacao) {
		return AvaliacaoDTO.builder()
				.id(avaliacao.getId())
				.userId(avaliacao.getUserAvaliacao().getUserId())
				.username(avaliacao.getUserAvaliacao().getUserName())
				.userImageUrl(avaliacao.getUserAvaliacao().getImageUrl())
				.obs(avaliacao.getObs())
				.createdDate(avaliacao.getCreatedDate())
				.rate(avaliacao.getRate())
				.build();
	}
}
