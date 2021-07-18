package com.housepass.imoveis.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.imoveis.app.entities.ValorImovel;

@Repository
public interface ValorImovelRepository extends MongoRepository<ValorImovel, String> {

}
