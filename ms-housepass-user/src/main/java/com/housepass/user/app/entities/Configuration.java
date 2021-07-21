package com.housepass.user.app.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.housepass.user.app.enums.TypeUserConfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Configuration {
	
	@Id
	private String id;
	
	private String userId;
	
	// habilita receber convites
	private boolean inviteReceive = true;
	
	// habilita exibir conta do usuario nas buscas da plataforma
	private boolean enableViewUserInSearches = true;
	
	// habilita exibir imoveis do usuario nas buscas da plataforma
	private boolean enableViewImoveisInSearches = true;
	
	// Usuario permite receber ofertas em seus imoveis
	private boolean allowReceiveOffersImoveis = true;
	
	// Que tipos de usuarios podem enviar ofertas em seus imoveis
	private TypeUserConfiguration whoSendOffersImoveis = TypeUserConfiguration.TODOS;
		
	// Usuario permite receber comentarios em seus imoveis
	private boolean allowReceiveCommentsImoveis = true;
	
	// Que tipos de usuarios podem enviar comentarios em seus imoveis
	private TypeUserConfiguration whoSendCommentsImoveis = TypeUserConfiguration.TODOS;
	
	
	// Usuario permite que outros usuarios compartilhem suas publicacoes
	private boolean allowSharesPublicationsImoveis = true;
	
		
	// Que tipos de usuarios podem compartilhar suas publcações
	private TypeUserConfiguration whoSharePublications = TypeUserConfiguration.TODOS;

}
