package com.housepass.timeline.app.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.housepass.timeline.app.enums.TypeTimelineEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Timeline {
	
	@Id
	private String id;
	
	private String userId;	
	private Long quantViews;
	private Long quantLikes;
	private Long quantShares;
	private Long quantComments;
	private TypeTimelineEnum typeTimeline;	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;	

}
