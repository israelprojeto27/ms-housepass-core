package com.housepass.message.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.message.app.entities.ItemMessage;

@Repository
public interface ItemMessageRepository extends MongoRepository<ItemMessage, String>{

}
