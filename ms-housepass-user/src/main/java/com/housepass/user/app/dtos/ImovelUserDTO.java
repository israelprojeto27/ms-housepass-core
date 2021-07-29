package com.housepass.user.app.dtos;

import java.time.LocalDateTime;

import com.housepass.user.app.entities.Imovel;

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
	private String userId;
	private String titulo;
	private String imoveImageUrl;
	private String localizacao;
	private LocalDateTime dateImovelCreate;
	
	private long quantLikes;
	private long quantViews;
	private long quantShares;
	private long quantComments;
	private long quantOffers;
	private long quantRecommendations;
	private long quantEvaluations;
	
	public static Imovel toEntity(ImovelUserDTO dto, String userId) {
		return Imovel.builder()
				.imovelId(dto.getImovelId())
				.userId(userId)
				.titulo(dto.getTitulo())
				.dateImovelCreate(dto.getDateImovelCreate())
				.imoveImageUrl(dto.getImoveImageUrl())
				.localizacao(dto.getLocalizacao())
				.quantLikes(0l)
				.quantViews(0l)
				.quantShares(0l)
				.quantComments(0l)
				.quantOffers(0l)
				.quantRecommendations(0l)
				.quantEvaluations(0l)
				.build();
	}
	
	public static ImovelUserDTO fromEntity(Imovel imovel) {
		return ImovelUserDTO.builder()
				.imovelId(imovel.getImovelId())
				.userId(imovel.getUserId())
				.titulo(imovel.getTitulo())
				.dateImovelCreate(imovel.getDateImovelCreate())
				.imoveImageUrl(imovel.getImoveImageUrl())
				.localizacao(imovel.getLocalizacao())
				.quantLikes(imovel.getQuantLikes())
				.quantViews(imovel.getQuantViews())
				.quantShares(imovel.getQuantShares())
				.quantComments(imovel.getQuantComments())
				.quantOffers(imovel.getQuantOffers())
				.quantRecommendations(imovel.getQuantRecommendations())
				.quantEvaluations(imovel.getQuantEvaluations())
				.build();
	}

}
