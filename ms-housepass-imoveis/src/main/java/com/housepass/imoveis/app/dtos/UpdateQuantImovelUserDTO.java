package com.housepass.imoveis.app.dtos;



import com.housepass.imoveis.app.enums.TypeQuantImovel;

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
