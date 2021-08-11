package com.housepass.user.app.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.user.app.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	User findByEmailAndPassword(String email, String password);

	User findByEmail(String email);

	List<User> findByTypeUserAndNameContainingIgnoreCase(String type, String name, PageRequest pageable);

	List<User> findByNameContainingIgnoreCase(String value, PageRequest pageable);

}
