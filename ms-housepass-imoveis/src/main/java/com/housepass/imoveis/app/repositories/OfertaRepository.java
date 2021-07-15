package com.housepass.imoveis.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.imoveis.app.entities.Oferta;

@Repository
public interface OfertaRepository extends MongoRepository<Oferta, String>{

}
