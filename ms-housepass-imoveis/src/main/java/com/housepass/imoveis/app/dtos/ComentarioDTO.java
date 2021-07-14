package com.housepass.imoveis.app.dtos;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.entities.Comentario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {
	
	private String id;
	private String userId;
	private String username;
	private String userImageUrl;
	private String descricao;
	private LocalDateTime dataComentario;
	
	public static ComentarioDTO fromEntity(Comentario comentario) {
		return ComentarioDTO.builder()
				.id(comentario.getId())
				.userId(comentario.getUserComentario().getUserId())
				.username(comentario.getUserComentario().getUsername())
				.userImageUrl(comentario.getUserComentario().getUserImageUrl())
				.descricao(comentario.getDescricao())
				.dataComentario(comentario.getData())
				.build();
	}

}
