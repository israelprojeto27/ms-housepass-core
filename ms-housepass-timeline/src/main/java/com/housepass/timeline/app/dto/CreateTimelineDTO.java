package com.housepass.timeline.app.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.housepass.timeline.app.entities.Timeline;
import com.housepass.timeline.app.enums.TypeTimelineEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTimelineDTO {
	
	private String userId;
	private TypeTimelineEnum typeTimeline;
	
	
	public static Timeline toEntity(CreateTimelineDTO dto) {
		return Timeline.builder()				
				.userId(dto.getUserId())
				.typeTimeline(dto.getTypeTimeline())
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())				
				.build();
	}

}
