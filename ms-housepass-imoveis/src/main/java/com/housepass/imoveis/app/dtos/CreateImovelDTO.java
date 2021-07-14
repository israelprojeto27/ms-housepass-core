package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.entities.Imovel;
import com.housepass.imoveis.app.enums.AcaoImovelEnum;
import com.housepass.imoveis.app.enums.TipoImovelEnum;
import com.housepass.imoveis.app.enums.TypeUserEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateImovelDTO {
		
	private double valor;
	private String titulo;
	private String localizacao;
	private TipoImovelEnum tipoImovel; 
	private AcaoImovelEnum acaoImovel;
	private String descricao;	

	private long area;
	private long quantBanheiro;
	private long quantQuarto;
	private long quantGaragem;
	private long quantSuite;
	private String mobiliado;
	
	private String userId;
	

	public static Imovel toEntity(CreateImovelDTO dto) {
		return Imovel.builder()
				.valor(dto.getValor())
				.descricao(dto.getDescricao())
				.tipoImovel(dto.getTipoImovel())
				.titulo(dto.getTitulo())
				.localizacao(dto.getLocalizacao())
				.acaoImovel(dto.getAcaoImovel())
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())		
				.area(dto.getArea())
				.quantBanheiro(dto.getQuantBanheiro())
				.quantGaragem(dto.getQuantGaragem())
				.quantQuarto(dto.getQuantQuarto())
				.quantSuite(dto.getQuantSuite())
				.mobiliado(dto.getMobiliado())
				.build();
	}

}
