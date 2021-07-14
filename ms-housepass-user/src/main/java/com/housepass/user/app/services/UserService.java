package com.housepass.user.app.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.user.app.dtos.ChangePasswordUserDTO;
import com.housepass.user.app.dtos.CreateUserDTO;
import com.housepass.user.app.dtos.ImovelUserDTO;
import com.housepass.user.app.dtos.UpdateImovelUserDTO;
import com.housepass.user.app.dtos.UpdateUserDTO;
import com.housepass.user.app.dtos.UserDTO;
import com.housepass.user.app.dtos.UserOwnerImovelDTO;
import com.housepass.user.app.entities.Imovel;
import com.housepass.user.app.entities.User;
import com.housepass.user.app.repositories.ImovelRepository;
import com.housepass.user.app.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;	
	
	@Autowired
	private ImovelRepository imovelRepository;
	

	@Transactional
	public void create(CreateUserDTO dto) {	
		User user = CreateUserDTO.toEntity(dto);	
		repository.insert(user);
	}

	public UserDTO findById(String userId) {	
		User user = repository.findById(userId).get();
		return UserDTO.fromEntity(user);
	}

	public List<UserDTO> findAll() {
		List<User> list = repository.findAll(); 
		return list.stream()
				.map(UserDTO::fromEntity)
				.collect(Collectors.toList());
	}

	public UserDTO findByEmailAndPassword(String login, String password) {
		User user = repository.findByEmailAndPassword(login, password);
		return UserDTO.fromEntity(user);
	}

	public UserOwnerImovelDTO findByIdOwnerImovel(String userId) {
		User user = repository.findById(userId).get();
		return UserOwnerImovelDTO.fromEntity(user);
	}

	@Transactional
	public ResponseEntity<?> updateUser(String userId, UpdateUserDTO dto) {
		User user = repository.findById(userId).get();		
		User userUpdate = repository.findByEmail(dto.getEmail());
		
		if (userUpdate != null && !user.getId().equals(userUpdate.getId())) {
			return new ResponseEntity<>("Já existe um outro usuario com o email informado", HttpStatus.BAD_REQUEST);
		}
		
		user.setAbout(dto.getAbout());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setLocation(dto.getLocation());
		user.setPhone(dto.getPhone());
		user.setWebSite(dto.getWebSite());
		repository.save(user);		
		return new ResponseEntity<>("Usuario atualizado com sucesso", HttpStatus.NO_CONTENT);
	}

	@Transactional
	public ResponseEntity<?> changePassword(String userId, ChangePasswordUserDTO dto) {

		User user = repository.findById(userId).get();
		if (! dto.getPassword().equals(dto.getConfirmPassword())) {
			return new ResponseEntity<>("Password e confirma password não conferem", HttpStatus.BAD_REQUEST);
		}
		
		user.setPassword(dto.getConfirmPassword());
		repository.save(user);		
		return new ResponseEntity<>("Password de usuario alterado com sucesso", HttpStatus.NO_CONTENT);
	}
	
	@Transactional
	public ResponseEntity<?> addImovel(String userId, ImovelUserDTO dto) {
		User user = repository.findById(userId).get();
		Imovel imovel = ImovelUserDTO.toEntity(dto, userId);
		
		imovelRepository.insert(imovel);
		if ( user.getImoveis() != null ) {
			user.getImoveis().add(imovel);
		}
		else {
			user.setImoveis(Arrays.asList(imovel));
		}
		
		repository.save(user);
		
		return new ResponseEntity<>("Imovel adicionado para a lista de imoveis do usuario", HttpStatus.CREATED);
	}

	@Transactional
	public ResponseEntity<?> updateImovelUser(String userId, String imovelId, UpdateImovelUserDTO dto) {		
		
		Imovel imovel = imovelRepository.findByUserIdAndImovelId(userId, imovelId);
		imovel.setTitulo(dto.getTitulo());
		imovel.setImoveImageUrl(dto.getImoveImageUrl());
		imovel.setLocalizacao(dto.getLocalizacao());
		imovel.setQuantComments(dto.getQuantComments());
		imovel.setQuantLikes(dto.getQuantLikes());
		imovel.setQuantShares(dto.getQuantShares());
		imovel.setQuantViews(dto.getQuantViews());
		
		imovelRepository.save(imovel);
		
		return new ResponseEntity<>("Informação do imovel atualizada com sucesso", HttpStatus.NO_CONTENT);
	}

}
