package com.housepass.imoveis.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.imoveis.app.entities.LikeImovel;

@Repository
public interface LikeImovelRepository extends MongoRepository<LikeImovel, String>{

	LikeImovel findByUserIdAndImovelId(String userId, String imovelId);

}
