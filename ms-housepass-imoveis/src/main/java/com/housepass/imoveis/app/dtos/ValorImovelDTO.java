package com.housepass.imoveis.app.dtos;

import com.housepass.imoveis.app.entities.ValorImovel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValorImovelDTO {

	private String id;
	private String descricao;
	private Double valor;
	
	public static ValorImovelDTO fromEntity(ValorImovel valorImovel) {
		return ValorImovelDTO.builder()
				.id(valorImovel.getId())
				.descricao(valorImovel.getDescricao())
				.valor(valorImovel.getValor())
				.build();
	}
}
