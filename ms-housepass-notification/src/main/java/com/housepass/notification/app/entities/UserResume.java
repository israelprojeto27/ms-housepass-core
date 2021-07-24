package com.housepass.notification.app.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserResume {
	
	@Id
	private String id;
	
	private String userId;
	private String userName;
	private String imageUrl;
	
	@DBRef(lazy = true)
	private List<Notification> notifications = new ArrayList<Notification>();

}
