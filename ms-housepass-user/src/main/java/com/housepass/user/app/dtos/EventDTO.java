package com.housepass.user.app.dtos;

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
public class EventDTO {
	
	private String id;
	
	private String title;
	private String descrption;
	private String location;
	private String date;
	private String hour;
	private String imageUrl;
	
	
	public static EventDTO fromEntity(Event event) {
		return EventDTO.builder()
				.id(event.getId())
				.title(event.getTitle())
				.descrption(event.getDescrption())
				.location(event.getLocation())
				.date(FormatUtils.converteLocalDateToString(event.getDate())) 
				.hour(FormatUtils.converteLocalTimeToString(event.getHour()))				
				.imageUrl(event.getImageUrl())
				.build();
	}

}
