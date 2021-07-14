package com.housepass.user.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.user.app.entities.UserResume;

@Repository
public interface UserResumeRepository extends MongoRepository<UserResume, String>{

	UserResume findByUserId(String userId);

}
