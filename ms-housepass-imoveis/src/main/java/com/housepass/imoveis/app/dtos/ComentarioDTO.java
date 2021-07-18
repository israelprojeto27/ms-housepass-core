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
	private String comentario;
	private LocalDateTime createdDate;
	
	public static ComentarioDTO fromEntity(Comentario comentario) {
		return ComentarioDTO.builder()
				.id(comentario.getId())
				.userId(comentario.getUserComentario().getUserId())
				.username(comentario.getUserComentario().getUserName())
				.userImageUrl(comentario.getUserComentario().getImageUrl())
				.comentario(comentario.getComentario())
				.createdDate(comentario.getCreatedDate())
				.build();
	}

}
