package com.housepass.imoveis.app.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValorImovel {
	
	@Id
	private String id;	
	
	private String descricao;
	private Double valor;
	private LocalDateTime createdDate;
	private String imovelId;

}
