package com.housepass.timeline.app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housepass.timeline.app.dto.CreateTimelineDTO;
import com.housepass.timeline.app.dto.TimelineDTO;
import com.housepass.timeline.app.entities.Timeline;
import com.housepass.timeline.app.repositories.TimelineRepository;

@Service
public class TimelineService {

	@Autowired
	TimelineRepository repository;
	

	
	@Transactional
	public void create(CreateTimelineDTO dto) {		
		Timeline timeline = CreateTimelineDTO.toEntity(dto);
		repository.insert(timeline);
	}

	public List<TimelineDTO> findAll() {
		List<Timeline> list = repository.findAll(); 
		return list.stream()
					.map(timeline -> TimelineDTO.fromEntity(timeline))
					.collect(Collectors.toList());
	}

	public TimelineDTO findById(String timelineId) {
		Optional<Timeline> timelineOpt = repository.findById(timelineId);
		return TimelineDTO.fromEntity(timelineOpt.get());
	}

}
