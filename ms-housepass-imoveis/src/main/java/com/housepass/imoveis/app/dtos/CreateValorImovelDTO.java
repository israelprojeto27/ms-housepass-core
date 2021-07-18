package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.entities.ValorImovel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateValorImovelDTO {
	
	private String imovelId;
	private String descricao;
	private Double valor;
	
	public static ValorImovel toEntity(CreateValorImovelDTO dto) {
		return ValorImovel.builder()
				.descricao(dto.getDescricao())
				.valor(dto.getValor())
				.imovelId(dto.getImovelId())
				.createdDate(LocalDateTime.now())
				.build();
	}

}
