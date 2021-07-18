package com.housepass.imoveis.app.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.imoveis.app.dtos.CreateValorImovelDTO;
import com.housepass.imoveis.app.dtos.ValorImovelDTO;
import com.housepass.imoveis.app.dtos.VisitanteDTO;
import com.housepass.imoveis.app.entities.Imovel;
import com.housepass.imoveis.app.entities.ValorImovel;
import com.housepass.imoveis.app.entities.Visitante;
import com.housepass.imoveis.app.exceptions.DataNotFoundException;
import com.housepass.imoveis.app.repositories.ImovelRepository;
import com.housepass.imoveis.app.repositories.ValorImovelRepository;

@Service
public class ValorImovelService {

	@Autowired
	private ValorImovelRepository repository;
	
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	
	@Transactional
	public ResponseEntity<?> create(CreateValorImovelDTO dto) {
		
		Imovel imovel = imovelRepository.findById(dto.getImovelId()).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		ValorImovel valorImovel = CreateValorImovelDTO.toEntity(dto);
		
		repository.insert(valorImovel);
		
		imovel.getValoresImovel().add(valorImovel);
		imovelRepository.save(imovel);
		
		return new ResponseEntity<>("Valor imovel foi adicionada com sucesso", HttpStatus.CREATED);
	}


	public ResponseEntity<?> findAll() {		
		return new ResponseEntity<>(repository.findAll().stream()
														.map(ValorImovelDTO::fromEntity)
														.collect(Collectors.toList()), 
									HttpStatus.OK);
	}


	public ResponseEntity<?> findById(String valorImovelId) {
		ValorImovel valorImovel = repository.findById(valorImovelId).orElseThrow(() -> new DataNotFoundException("Valor imovel não encontrado"));
		return new ResponseEntity<>(ValorImovelDTO.fromEntity(valorImovel), HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<?> delete(String valorImovelId) {
		ValorImovel valorImovel = repository.findById(valorImovelId).orElseThrow(() -> new DataNotFoundException("Valor imovel não encontrado"));
		Imovel imovel = imovelRepository.findById(valorImovel.getImovelId()).orElseThrow(() -> new DataNotFoundException("Imóvel não encontrado"));
		
		imovel.getValoresImovel().remove(valorImovel);
		imovelRepository.save(imovel);
		
		repository.delete(valorImovel);		
		return new ResponseEntity<>("Valor imovel foi removido com sucesso", HttpStatus.NO_CONTENT);
	}
}
