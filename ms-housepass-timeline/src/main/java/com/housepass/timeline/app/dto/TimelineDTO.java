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
@NoArgsConstructor
@AllArgsConstructor
public class TimelineDTO {
	
	private String id;	
	private String userId;	
	private Long quantViews;
	private Long quantLikes;
	private Long quantShares;
	private Long quantComments;
	private TypeTimelineEnum typeTimeline;	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;	
	
	
	public static TimelineDTO fromEntity(Timeline timeline) {
		return TimelineDTO.builder()
				.id(timeline.getId())
				.userId(timeline.getUserId())
				.quantComments(timeline.getQuantComments())
				.quantLikes(timeline.getQuantLikes())
				.quantShares(timeline.getQuantShares())
				.quantViews(timeline.getQuantViews())
				.typeTimeline(timeline.getTypeTimeline())
				.createdAt(timeline.getCreatedAt())
				.build();
	}
	

}
