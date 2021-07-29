package com.housepass.user.app.dtos;

import com.housepass.user.app.enums.TypeQuantImovel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuantImovelUserDTO {
	
	private TypeQuantImovel typeQuant;	

}
