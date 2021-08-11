package com.housepass.imoveis.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.imoveis.app.dtos.ActionLikeUnLikeDTO;
import com.housepass.imoveis.app.dtos.CreateImovelDTO;
import com.housepass.imoveis.app.dtos.UpdateImovelDTO;
import com.housepass.imoveis.app.services.ImovelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "ImovelController", tags = "Imovel", description = "REST Api de MS Imovel")
@RestController
@RequestMapping("/imovel")
public class ImovelController {
	
	@Autowired
	private ImovelService service;
 
	
	@ApiOperation(value = "Buscar imoveis por diversos parametros")
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(value = "page") int page, 
								    @RequestParam(value = "size") int size,						
								    @RequestParam(value = "action", required = true) String action,
								    @RequestParam(value = "type", required = false) String type,
								    @RequestParam(value = "priceMin", required = false) String priceMin,		
								    @RequestParam(value = "priceMax", required = false) String priceMax,		
								    @RequestParam(value = "quantQuartos", required = false) String quantQuartos,
								    @RequestParam(value = "quantBanheiros", required = false) String quantBanheiros,
								    @RequestParam(value = "quantGaragem", required = false) String quantGaragem,
								    @RequestParam(value = "quantSuite", required = false) String quantSuite,
								    @RequestParam(value = "areaMin", required = false) String areaMin,
								    @RequestParam(value = "areaMax", required = false) String areaMax) {
		
		return service.searchImoveisCustom(action, type, priceMin, priceMax,
				 quantQuartos, quantBanheiros, quantGaragem, quantSuite, areaMin, areaMax,
				 page, size);
	}
 
	@ApiOperation(value = "Cadastro de um novo imóvel")
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody CreateImovelDTO dto){
		return service.create(dto);		
	}
	
	@ApiOperation(value = "Atualiza informações do imóvel")
	@PatchMapping("/{imovelId}")
	public ResponseEntity<?> update(@PathVariable String imovelId,
									@RequestBody UpdateImovelDTO dto){		
		return service.update(imovelId, dto);
	}
	
	
	@ApiOperation(value = "Buscar todos os imoveis")
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Filtrar os imoveis cadastrados")
	@PostMapping("/filter")
	public ResponseEntity<?> findByFilter(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		return service.findByFilter(page, size);
	}
	
	
	
	@ApiOperation(value = "Buscar imovel por Id")
	@GetMapping("/{imovelId}")
	public ResponseEntity<?> findById(@PathVariable String imovelId) {
		return service.findById(imovelId);
	}

	
	@ApiOperation(value = "Adiciona like ou unlike para um imovel")
	@PostMapping("/{imovelId}")
	public ResponseEntity<?> actionLikeUnlikeImovelId(@PathVariable String imovelId, @RequestBody ActionLikeUnLikeDTO dto) {
		return service.actionLikeUnlikeImovelId(imovelId, dto);
	}
	

}
