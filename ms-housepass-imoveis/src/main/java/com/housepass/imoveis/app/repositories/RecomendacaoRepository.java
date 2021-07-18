package com.housepass.imoveis.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.imoveis.app.entities.Recomendacao;

@Repository
public interface RecomendacaoRepository extends MongoRepository<Recomendacao, String>{

}
