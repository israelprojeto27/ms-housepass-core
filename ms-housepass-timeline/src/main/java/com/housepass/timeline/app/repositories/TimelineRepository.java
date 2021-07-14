package com.housepass.timeline.app.repositories;

import java.util.UUID;
 
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.timeline.app.entities.Timeline;

@Repository
public interface TimelineRepository extends MongoRepository<Timeline, String>{

}
