package com.housepass.user.app.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Imovel {
	
	@Id
	private String id;
	
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
	

}
