package com.housepass.user.app.dtos;

import java.time.LocalDateTime;

import com.housepass.user.app.entities.Invite;
import com.housepass.user.app.enums.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInviteDTO {
	
	private String userSendInviteId;
	private String userReceiveInviteId;
	private String message;
	
	public static Invite toEntity(CreateInviteDTO dto) {
		return Invite.builder()
				.message(dto.getMessage())
				.createdDate(LocalDateTime.now())
				.updatedDate(LocalDateTime.now())
				.statusInvite(StatusEnum.SENT)
				.userReceiveInviteId(dto.getUserReceiveInviteId())
				.build();
	}

}
