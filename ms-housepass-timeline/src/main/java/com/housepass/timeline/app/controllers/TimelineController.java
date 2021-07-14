package com.housepass.timeline.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housepass.timeline.app.dto.CreateTimelineDTO;
import com.housepass.timeline.app.services.TimelineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "TimelineController", tags = "Timeline", description = "REST Api de MS Timeline")
@RestController
@RequestMapping("/timeline")
public class TimelineController {
	
	@Autowired
	private TimelineService service;
	
	
	@ApiOperation(value = "Listar todas publicações de timeline")
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Buscar um publicação de timeline por Id")
	@GetMapping("/{timelineId}")
	public ResponseEntity<?> findById(@PathVariable String timelineId) {
		return new ResponseEntity<>(service.findById(timelineId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Criar uma publicação de timeline")
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody CreateTimelineDTO dto){
		service.create(dto);
		return new ResponseEntity<>("Timeline criada com sucesso", HttpStatus.CREATED);
	}

}
