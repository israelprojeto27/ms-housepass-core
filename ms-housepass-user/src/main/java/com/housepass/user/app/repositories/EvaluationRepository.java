package com.housepass.user.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.housepass.user.app.entities.Evaluation;

@Repository
public interface EvaluationRepository extends MongoRepository<Evaluation, String>{

}
