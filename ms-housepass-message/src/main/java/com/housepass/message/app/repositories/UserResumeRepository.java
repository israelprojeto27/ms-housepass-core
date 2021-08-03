package com.housepass.message.app.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.message.app.entities.Message;
import com.housepass.message.app.entities.UserResume;

@Repository
public interface UserResumeRepository extends MongoRepository<UserResume, String>{

	Optional<UserResume> findByUserId(String userResumeSendId);

}
