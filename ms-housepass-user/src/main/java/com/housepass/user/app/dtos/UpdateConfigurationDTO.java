package com.housepass.user.app.dtos;

import com.housepass.user.app.enums.TypeUserConfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateConfigurationDTO {	
		
	private boolean inviteReceive;
	private boolean enableViewUserInSearches;
	private boolean enableViewImoveisInSearches;
	private boolean allowReceiveOffersImoveis;
	private TypeUserConfiguration whoSendOffersImoveis;
	private boolean allowReceiveCommentsImoveis;
	private TypeUserConfiguration whoSendCommentsImoveis;
	private boolean allowSharesPublicationsImoveis;
	private TypeUserConfiguration whoSharePublications;

}
