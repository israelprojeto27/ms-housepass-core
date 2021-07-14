package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
@NoArgsConstructor
@AllArgsConstructor
public class ImovelDTO {
	
	private String id;
		
	private String titulo;
	private String localizacao;
	private TipoImovelEnum tipoImovel; 
	private AcaoImovelEnum acaoImovel;
	private String descricao;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private String userId;
	private String userName;
	private String userImageUrl;
	private TypeUserEnum tipoUsuario;
	
	private List<OfertaDTO> ofertas;
	private List<ComentarioDTO> comentarios;
	private List<ValorImovelDTO> valoresImovel;
	private List<AvaliacaoDTO> avaliacoes;	
	private List<RecomendacaoDTO> recomendacoes;
	
	private long quantLikes;
	private long quantViews;
	private long quantShares;
	private long quantComments;
	
	private double area;
	private long quantBanheiro;
	private long quantQuarto;
	private long quantGaragem;
	private long quantSuite;
	private String mobiliado;
	
	
	public static ImovelDTO fromEntity(Imovel imovel) {
		return ImovelDTO.builder()
				.id(imovel.getId())			
				.titulo(imovel.getTitulo())
				.tipoImovel(imovel.getTipoImovel())
				.acaoImovel(imovel.getAcaoImovel())
				.descricao(imovel.getDescricao())
				.localizacao(imovel.getLocalizacao())
				.createdAt(imovel.getCreatedAt())
				.userId(imovel.getUserOwner().getUserId())
				.userName(imovel.getUserOwner().getUsername())
				.userImageUrl(imovel.getUserOwner().getUserImageUrl())
				.tipoUsuario(imovel.getUserOwner().getTypeUser())
				.area(imovel.getArea())
				.quantBanheiro(imovel.getQuantBanheiro())
				.quantQuarto(imovel.getQuantQuarto())
				.quantGaragem(imovel.getQuantGaragem())
				.quantSuite(imovel.getQuantSuite())
				.mobiliado(imovel.getMobiliado())				
				.ofertas(imovel.getOfertas() != null ? imovel.getOfertas().stream().map(offer -> OfertaDTO.fromEntity(offer)).collect(Collectors.toList()) : null)
				.comentarios(imovel.getComentarios() != null ? imovel.getComentarios().stream().map(comm -> ComentarioDTO.fromEntity(comm)).collect(Collectors.toList()) : null)
				.valoresImovel(imovel.getValoresImovel() != null ? imovel.getValoresImovel().stream().map(val -> ValorImovelDTO.fromEntity(val)).collect(Collectors.toList()) : null)
				.avaliacoes(imovel.getAvaliacoes() != null ? imovel.getAvaliacoes().stream().map(aval -> AvaliacaoDTO.fromEntity(aval)).collect(Collectors.toList()) : null)
				.recomendacoes(imovel.getRecomendacoes() != null ? imovel.getRecomendacoes().stream().map(recom -> RecomendacaoDTO.fromEntity(recom)).collect(Collectors.toList()) : null)
				.build();
	}

}
