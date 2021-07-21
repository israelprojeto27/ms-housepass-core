package com.housepass.user.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.user.app.dtos.UpdateConfigurationDTO;
import com.housepass.user.app.entities.Configuration;
import com.housepass.user.app.entities.User;
import com.housepass.user.app.exceptions.DataNotFoundException;
import com.housepass.user.app.repositories.ConfigurationRepository;
import com.housepass.user.app.repositories.UserRepository;

@Service
public class ConfigurationService {
	
	@Autowired
	private ConfigurationRepository repository;
	
	@Autowired
	private UserRepository userRepository;

	
	@Transactional
	public ResponseEntity<?> create(String userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("Usuário não encontrado"));
		
		Configuration configuration = new Configuration();
		configuration.setUserId(userId);		
		repository.insert(configuration);
		
		user.setConfiguration(configuration);
		userRepository.save(user);
		
		return new ResponseEntity<>("Configuração do usuario foi adicionada com sucesso", HttpStatus.CREATED);
	}
	
	@Transactional
	public boolean createConfigurationForUser(String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("Usuário não encontrado"));
		
		Configuration configuration = new Configuration();
		configuration.setUserId(userId);		
		repository.insert(configuration);
		
		user.setConfiguration(configuration);
		userRepository.save(user);
		
		return true;
	}

	@Transactional
	public ResponseEntity<?> update(String userId, UpdateConfigurationDTO dto) {
		User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("Usuário não encontrado"));
		Configuration configuration = user.getConfiguration();
		
		configuration.setAllowReceiveCommentsImoveis(dto.isAllowReceiveCommentsImoveis());
		configuration.setAllowReceiveOffersImoveis(dto.isAllowReceiveOffersImoveis());
		configuration.setAllowSharesPublicationsImoveis(dto.isAllowSharesPublicationsImoveis());
		configuration.setEnableViewImoveisInSearches(dto.isEnableViewImoveisInSearches());
		configuration.setEnableViewUserInSearches(dto.isEnableViewUserInSearches());
		
		configuration.setInviteReceive(dto.isInviteReceive());
		configuration.setWhoSendCommentsImoveis(dto.getWhoSendCommentsImoveis());
		configuration.setWhoSendOffersImoveis(dto.getWhoSendOffersImoveis());
		configuration.setWhoSharePublications(dto.getWhoSharePublications());	
		
		repository.save(configuration);
		
		return new ResponseEntity<>("Configuração do usuario foi atualizada com sucesso", HttpStatus.NO_CONTENT);
	}

}
