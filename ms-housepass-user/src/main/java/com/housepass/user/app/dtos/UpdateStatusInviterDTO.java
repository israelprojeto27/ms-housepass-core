package com.housepass.user.app.dtos;

import com.housepass.user.app.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusInviterDTO {

	private String inviteId;
	private String userReceiveInviteId;
	private StatusEnum statusInvite;
}


