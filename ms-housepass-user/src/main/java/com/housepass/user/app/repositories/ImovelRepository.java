package com.housepass.user.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.user.app.entities.Imovel;

@Repository
public interface ImovelRepository extends MongoRepository<Imovel, String>{

	Imovel findByUserIdAndImovelId(String userId, String imovelId);

	Imovel findByImovelIdAndUserId(String imovelId, String userId);

}
