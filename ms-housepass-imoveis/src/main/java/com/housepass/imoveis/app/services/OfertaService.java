package com.housepass.imoveis.app.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.imoveis.app.dtos.CreateOfertaDTO;
import com.housepass.imoveis.app.dtos.OfertaDTO;
import com.housepass.imoveis.app.dtos.VisitanteDTO;
import com.housepass.imoveis.app.entities.Imovel;
import com.housepass.imoveis.app.entities.Oferta;
import com.housepass.imoveis.app.entities.UserResume;
import com.housepass.imoveis.app.exceptions.DataNotFoundException;
import com.housepass.imoveis.app.repositories.ImovelRepository;
import com.housepass.imoveis.app.repositories.OfertaRepository;
import com.housepass.imoveis.app.repositories.UserResumeRepository;

@Service
public class OfertaService {
	
	@Autowired
	private OfertaRepository repository;
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	@Autowired
	private UserResumeRepository userResumeRepository;

	@Transactional
	public ResponseEntity<?> create(CreateOfertaDTO dto) {
		
		Imovel imovel = imovelRepository.findById(dto.getImovelId()).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		
		Oferta oferta = CreateOfertaDTO.toEntity(dto);
		UserResume userResume = userResumeRepository.findByUserId(dto.getUserId());
		oferta.setUserOferta(userResume);
		repository.insert(oferta);
		
		imovel.getOfertas().add(oferta);
		imovelRepository.save(imovel);		
		
		return new ResponseEntity<>("Oferta foi adicionada com sucesso", HttpStatus.CREATED);
	}

	public ResponseEntity<?> findAll() {		
		return new ResponseEntity<>(repository.findAll().stream()
														.map(OfertaDTO::fromEntity)
														.collect(Collectors.toList()), 
									HttpStatus.OK);
	}
	

	public ResponseEntity<?> findById(String ofertaId) {
		Oferta oferta = repository.findById(ofertaId).orElseThrow(() -> new DataNotFoundException("Oferta não encontrada"));
		return new ResponseEntity<>(OfertaDTO.fromEntity(oferta), HttpStatus.OK);
	}

	public ResponseEntity<?> delete(String ofertaId) {
		Oferta oferta = repository.findById(ofertaId).orElseThrow(() -> new DataNotFoundException("Oferta não encontrada"));
		Imovel imovel = imovelRepository.findById(oferta.getImovelId()).orElseThrow(() -> new DataNotFoundException("Imóvel não encontrado"));
		
		imovel.getOfertas().remove(oferta);
		imovelRepository.save(imovel);
		
		repository.delete(oferta);
		
		return new ResponseEntity<>("Oferta foi removida com sucesso", HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<?> findByFilter(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("createdDate").descending());
		return new ResponseEntity<>(repository.findAll(pageable).stream()
											  .map(OfertaDTO::fromEntity)
											  .collect(Collectors.toList()), 
				HttpStatus.OK);
	}

	public ResponseEntity<?> findByImovelId(String imovelId) {
		Imovel imovel = imovelRepository.findById(imovelId).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		
		return new ResponseEntity<>(imovel.getOfertas().stream()
														.map(OfertaDTO::fromEntity)
														.collect(Collectors.toList()), 
									HttpStatus.OK);
	}

	public ResponseEntity<?> findByFilterByImovelId(String imovelId, int page, int size) {
		Imovel imovel = imovelRepository.findById(imovelId).orElseThrow(() -> new DataNotFoundException("Imovel não encontrado"));
		
		return new ResponseEntity<>(imovel.getOfertas().stream()
														.skip(page * size)
														.limit(size)
														.map(OfertaDTO::fromEntity)
														.collect(Collectors.toList()), 
									HttpStatus.OK);
	}
	
	

}
