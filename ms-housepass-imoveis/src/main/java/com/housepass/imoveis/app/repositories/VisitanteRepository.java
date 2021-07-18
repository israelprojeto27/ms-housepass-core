package com.housepass.imoveis.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.imoveis.app.entities.Visitante;

@Repository
public interface VisitanteRepository extends MongoRepository<Visitante, String>{

}
