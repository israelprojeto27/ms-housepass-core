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
public class InviteDTO {
		
	private String id;
	private String message;
	private StatusEnum statusInvite;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private String userReceiveId;
	
	private UserResumeDTO userSendInvite;
	
	public static InviteDTO fromEntity(Invite invite) {
		return InviteDTO.builder()
						.id(invite.getId())
						.message(invite.getMessage())
						.createdDate(invite.getCreatedDate())
						.updatedDate(invite.getUpdatedDate())
						.statusInvite(invite.getStatusInvite())
						.userReceiveId(invite.getUserReceiveInviteId())
						.userSendInvite(UserResumeDTO.fromEntity(invite.getUserSendInvite()))
						.build();
	}

}
