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
public class Recomendacao {
	
	@Id
	private String id;
	
	private String imovelId;
	private LocalDateTime data;
	private String descricao;
	
	@DBRef
	private User userRecomendacao;

}
