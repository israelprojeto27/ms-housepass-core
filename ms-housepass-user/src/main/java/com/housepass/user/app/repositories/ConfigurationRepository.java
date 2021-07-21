package com.housepass.user.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.user.app.entities.Configuration;

@Repository
public interface ConfigurationRepository extends MongoRepository<Configuration, String>{

}
