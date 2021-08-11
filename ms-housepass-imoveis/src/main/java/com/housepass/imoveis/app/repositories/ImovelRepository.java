package com.housepass.imoveis.app.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.imoveis.app.entities.Imovel;

@Repository
public interface ImovelRepository  extends MongoRepository<Imovel, String> {

}
