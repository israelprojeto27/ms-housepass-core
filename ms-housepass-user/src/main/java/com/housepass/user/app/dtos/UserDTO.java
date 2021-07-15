package com.housepass.user.app.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.housepass.user.app.entities.User;
import com.housepass.user.app.enums.TypeUserEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
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
	
	private long quantConnections;
	private long quantImoveis;
	private long quantPublications;
	
	private List<ConnectionUserDTO> connections;
	private List<EventDTO> events;
	private List<RecommendationDTO> recommendations;
	private List<EvaluationDTO> evaluations;
	private List<ConquerDTO> conquers;
	private List<InviteDTO> invites;
	
	
	public static UserDTO fromEntity(User user) {
		return UserDTO.builder()			
				.id(user.getId())
				.email(user.getEmail())
				.name(user.getName())
				.password(user.getPassword())
				.location(user.getLocation())
				.phone(user.getPhone())
				.webSite(user.getWebSite())
				.typeUser(user.getTypeUser())
				.about(user.getAbout())
				.imageUserUrl(user.getImageUserUrl())
				.quantConnections(user.getQuantConnections())
				.quantImoveis(user.getQuantImoveis())
				.quantPublications(user.getQuantPublications())
				.connections(user.getConnections() != null && !user.getConnections().isEmpty() ? user.getConnections().stream().map(ConnectionUserDTO::fromEntity).collect(Collectors.toList()) : null)
				.events(user.getEvents() != null && !user.getEvents().isEmpty() ? user.getEvents().stream().map(EventDTO::fromEntity).collect(Collectors.toList()) : null)
				.recommendations(user.getRecommendations() != null && !user.getRecommendations().isEmpty() ? user.getRecommendations().stream().map(RecommendationDTO::fromEntity).collect(Collectors.toList()) : null)
				.evaluations(user.getEvaluations() != null && !user.getEvaluations().isEmpty() ? user.getEvaluations().stream().map(EvaluationDTO::fromEntity).collect(Collectors.toList()) : null)
				.conquers(user.getConquers() != null && !user.getConquers().isEmpty() ? user.getConquers().stream().map(ConquerDTO::fromEntity).collect(Collectors.toList()) : null)				
				.invites(user.getInvites() != null && !user.getInvites().isEmpty() ? user.getInvites().stream().map(InviteDTO::fromEntity).collect(Collectors.toList()) : null)
				.build();
	}
	
	public static UserDTO fromEntityWithoutListSubDocuments(User user) {
		return UserDTO.builder()			
				.id(user.getId())
				.email(user.getEmail())
				.name(user.getName())
				.password(user.getPassword())
				.location(user.getLocation())
				.phone(user.getPhone())
				.webSite(user.getWebSite())
				.typeUser(user.getTypeUser())
				.about(user.getAbout())
				.imageUserUrl(user.getImageUserUrl())
				.quantConnections(user.getQuantConnections())
				.quantImoveis(user.getQuantImoveis())
				.quantPublications(user.getQuantPublications())				
				.build();
	}
}






