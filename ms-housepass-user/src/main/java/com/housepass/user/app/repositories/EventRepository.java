package com.housepass.user.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.user.app.entities.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String>{

}
