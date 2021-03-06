package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.entities.Oferta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfertaDTO {
	
	private String id;
	private String userId;
	private String username;
	private String userImageUrl;
	private Double valorOferta;
	private LocalDateTime createdData;
	
	public static OfertaDTO fromEntity(Oferta oferta) {
		return OfertaDTO.builder()
				.id(oferta.getId())
				.userId(oferta.getUserOferta().getUserId())
				.username(oferta.getUserOferta().getUserName())
				.userImageUrl(oferta.getUserOferta().getImageUrl())
				.valorOferta(oferta.getValor())
				.createdData(oferta.getCreatedDate())
				.build();
	}

}
