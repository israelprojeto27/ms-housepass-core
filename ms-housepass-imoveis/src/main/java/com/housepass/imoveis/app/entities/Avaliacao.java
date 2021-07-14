package com.housepass.imoveis.app.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
public class Avaliacao {

	@Id
	private String id;
	
	private String imovelId;
	private LocalDateTime data;
	private Integer rate; 		// avaliacao que pode variar de 1 a 5 
	private String descricao;
	
	@DBRef
	private User userAvaliacao;
	
}
