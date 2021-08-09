package com.housepass.imoveis.app.dtos;

import com.housepass.imoveis.app.enums.TypeActionLike;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActionLikeUnLikeDTO {

	private String userId;
	private TypeActionLike action;
}
