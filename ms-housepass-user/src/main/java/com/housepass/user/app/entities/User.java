package com.housepass.user.app.entities;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.housepass.user.app.enums.TypeUserEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {
	

	@Id
	private String id;
	
	private String name;
	private String email;
	private String password;
	private String location;
	private String phone;
	private String webSite;
	private TypeUserEnum typeUser;
	private String about;
	private String imageUserUrl;	
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	private long quantConnections;
	private long quantImoveis;
	private long quantPublications;
	
	@DBRef(lazy = true)
	private List<ConnectionUser> connections;
	
	
	@DBRef(lazy = true)
	private List<Event> events;
	
	
	@DBRef(lazy = true)
	private List<Recommendation> recommendations;
	
	
	@DBRef(lazy = true)
	private List<Evaluation> evaluations;
	
	
	@DBRef(lazy = true)
	private List<Conquer> conquers;
	
	@DBRef(lazy = true)
	private List<Imovel> imoveis;
	
	@DBRef(lazy = true)
	private List<Invite> invites;	
	
}
