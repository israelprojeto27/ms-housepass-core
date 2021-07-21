package com.housepass.user.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.user.app.entities.Imovel;
import com.housepass.user.app.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	User findByEmailAndPassword(String email, String password);

	User findByEmail(String email);

}
