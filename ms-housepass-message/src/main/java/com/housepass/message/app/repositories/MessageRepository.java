package com.housepass.message.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.message.app.entities.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String>{

}
