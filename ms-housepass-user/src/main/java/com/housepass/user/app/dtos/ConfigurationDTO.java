package com.housepass.user.app.dtos;

import com.housepass.user.app.entities.Configuration;
import com.housepass.user.app.enums.TypeUserConfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationDTO {
	
	private boolean inviteReceive;
	private boolean enableViewUserInSearches;
	private boolean enableViewImoveisInSearches;
	private boolean allowReceiveOffersImoveis;
	private TypeUserConfiguration whoSendOffersImoveis;
	private boolean allowReceiveCommentsImoveis;
	private TypeUserConfiguration whoSendCommentsImoveis;
	private boolean allowSharesPublicationsImoveis;
	private TypeUserConfiguration whoSharePublications;
	
	public static ConfigurationDTO fromEntity(Configuration confi) {
		return ConfigurationDTO.builder()
				.allowReceiveCommentsImoveis(confi.isAllowReceiveCommentsImoveis())
				.allowReceiveOffersImoveis(confi.isAllowReceiveOffersImoveis())
				.allowSharesPublicationsImoveis(confi.isAllowSharesPublicationsImoveis())
				.enableViewImoveisInSearches(confi.isEnableViewImoveisInSearches())
				.enableViewUserInSearches(confi.isEnableViewUserInSearches())			
				.inviteReceive(confi.isInviteReceive())
				.whoSendCommentsImoveis(confi.getWhoSendCommentsImoveis())
				.whoSendOffersImoveis(confi.getWhoSendOffersImoveis())
				.whoSharePublications(confi.getWhoSharePublications())				
				.build();
	}

}
