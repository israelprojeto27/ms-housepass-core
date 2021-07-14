package com.housepass.imoveis.app.dtos;

import com.housepass.imoveis.app.entities.Imovel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateImovelDTO {
	
	private String titulo;
	private String imoveImageUrl;
	private String localizacao;
	
	private long quantLikes;
	private long quantViews;
	private long quantShares;
	private long quantComments;
	
	public static Imovel toEntity(UpdateImovelDTO dto, String userId) {
		return Imovel.builder()				
				.titulo(dto.getTitulo())				
				.imageUrl(dto.getImoveImageUrl())
				.localizacao(dto.getLocalizacao())
				.quantLikes(dto.getQuantLikes())
				.quantViews(dto.getQuantViews())
				.quantShares(dto.getQuantShares())
				.quantComments(dto.getQuantComments())
				.build();
	}
}


