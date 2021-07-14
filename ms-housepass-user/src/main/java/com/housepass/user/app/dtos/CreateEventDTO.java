package com.housepass.user.app.dtos;

import java.time.LocalTime;

import com.housepass.user.app.entities.Event;
import com.housepass.user.app.utils.FormatUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventDTO {
	
	private String title;
	private String descrption;
	private String location;
	private String date;
	private String hour;
	private String imageUrl;
	
	private String userCreateEventId;
	
	public static Event toEntity(CreateEventDTO dto) {
		return Event.builder()
				.title(dto.getTitle())
				.descrption(dto.getDescrption())
				.location(dto.getLocation())
				.date(FormatUtils.converteStringToLocalDateTime(dto.getDate()))
				.hour(LocalTime.parse(dto.getHour()))
				.imageUrl(dto.getImageUrl())
				.build();
	}

}
