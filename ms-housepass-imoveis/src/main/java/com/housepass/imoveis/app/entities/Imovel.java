package com.housepass.imoveis.app.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.housepass.imoveis.app.enums.AcaoImovelEnum;
import com.housepass.imoveis.app.enums.TipoImovelEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imovel {

	@Id
	private String id;
	
	private double valor;
	private String titulo;
	private String localizacao;
	private TipoImovelEnum tipoImovel; 
	private AcaoImovelEnum acaoImovel;
	private String descricao;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String imageUrl;
	
	private double area;
	private long quantBanheiro;
	private long quantQuarto;
	private long quantGaragem;
	private long quantSuite;
	private String mobiliado;
	

	private long quantLikes;
	private long quantViews;
	private long quantShares;
	private long quantComments;
	
	private User userOwner;
	
	@DBRef(lazy = true)
	private List<Oferta> ofertas = new ArrayList<Oferta>();
	
	@DBRef(lazy = true)
	private List<Comentario> comentarios;
	
	@DBRef(lazy = true)
	private List<Recomendacao> recomendacoes;
	
	@DBRef(lazy = true)
	private List<Avaliacao> avaliacoes;
	
	@DBRef(lazy = true)
	private List<ValorImovel> valoresImovel;	
	
	@DBRef(lazy = true)
	private List<Visitante> visitantes;
	

	
	
}
