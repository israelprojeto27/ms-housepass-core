package com.housepass.user.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateImovelUserDTO {
	
	private String titulo;
	private String imoveImageUrl;
	private String localizacao;
	
	private long quantLikes;
	private long quantViews;
	private long quantShares;
	private long quantComments;


}
