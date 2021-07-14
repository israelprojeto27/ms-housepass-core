package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.entities.Imovel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImovelUserDTO {
	
	private String imovelId;
	private String titulo;
	private String imoveImageUrl;
	private String localizacao;
	private LocalDateTime dateImovelCreate;
	
	private long quantLikes;
	private long quantViews;
	private long quantShares;
	private long quantComments;	
	 
	
	public static ImovelUserDTO fromEntity(Imovel imovel) {
		return ImovelUserDTO.builder()
				.imovelId(imovel.getId())
				.titulo(imovel.getTitulo())
				.dateImovelCreate(imovel.getCreatedAt())
				.imoveImageUrl(imovel.getImageUrl())
				.localizacao(imovel.getLocalizacao())
				.quantLikes(imovel.getQuantLikes())
				.quantViews(imovel.getQuantViews())
				.quantShares(imovel.getQuantShares())
				.quantComments(imovel.getQuantComments())
				.build();
	}

}
