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
public class CreateComentarioDTO {
	
	private String imovelId;
	private String userId;
	private String comentario;	
	
	public static Comentario toEntity(CreateComentarioDTO dto) {
		return Comentario.builder()
				.imovelId(dto.getImovelId())
				.comentario(dto.getComentario())				
				.createdDate(LocalDateTime.now())
				.build();
	}
	

}
