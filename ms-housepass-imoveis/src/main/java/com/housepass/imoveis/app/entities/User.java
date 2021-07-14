package com.housepass.imoveis.app.entities;

import java.time.LocalDateTime;

import com.housepass.imoveis.app.enums.TypeUserEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private String userId;
	private String username;
	private String userImageUrl;
	private TypeUserEnum typeUser;
	
	private LocalDateTime lastChangeInfoUser; // campo que pode ser utilizado para auxiliar na verificação se houve alterações em informações do usuario. 
											  // E se por acaso houver entao promover as atualizacoes dos campos deste documento 
}
