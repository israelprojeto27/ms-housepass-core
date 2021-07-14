package com.housepass.user.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

	@Id
	private String id;
	
	private String title;
	private String descrption;
	private String location;
	private LocalDate date;
	private LocalTime hour;
	private String imageUrl;
	
	@DBRef
	private UserResume userCreateEvent; 
	
	
}
