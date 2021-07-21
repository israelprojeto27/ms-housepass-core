package com.housepass.user.app.entities;


import java.time.LocalDateTime;
import java.util.ArrayList;
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
	
	@DBRef
	private Configuration configuration;	
	
	
	@DBRef(lazy = true)
	private List<ConnectionUser> connections = new ArrayList<ConnectionUser>();
	
	
	@DBRef(lazy = true)
	private List<Event> events = new ArrayList<Event>();
	
	
	@DBRef(lazy = true)
	private List<Recommendation> recommendations = new ArrayList<Recommendation>();
	
	
	@DBRef(lazy = true)
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();
	
	
	@DBRef(lazy = true)
	private List<Conquer> conquers = new ArrayList<Conquer>();
	
	
	
	@DBRef(lazy = true)
	private List<Imovel> imoveis = new ArrayList<Imovel>();
	
	
	
	@DBRef(lazy = true)
	private List<Invite> invites = new ArrayList<Invite>();	
	
}
