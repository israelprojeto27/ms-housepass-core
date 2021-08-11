package com.housepass.imoveis.app.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.housepass.imoveis.app.entities.Imovel;

@Repository
public class ImovelCustomRepository implements IImovelCustomRepository{
	
	
	@PersistenceContext(unitName = "data")
	EntityManager em;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<Imovel> findAllCustom(String action, String type, String priceMin, String priceMax,  
								      String quantQuartos,	String  quantBanheiros, String quantGaragem,
								      String quantSuite, String areaMin, String areaMax, 
								      int page, int size) {
		
		List<Criteria> criterias = new ArrayList<Criteria>();		
		
		
		
		criterias.add(Criteria.where("acaoImovel").is(action));
		
		if ( type != null) {
			criterias.add(Criteria.where("tipoImovel").is(type));
		}		
		
		if ( priceMin != null) {
			criterias.add(Criteria.where("valor").gte(Double.valueOf(priceMin)));
		}
		
		if ( priceMax != null) {
			criterias.add(Criteria.where("valor").lte(Double.valueOf(priceMax)));
		}
		
		if ( quantQuartos != null ) {
			criterias.add(Criteria.where("quantQuarto").gte(Long.valueOf(quantQuartos)));
		}
		
		if ( quantBanheiros != null ) {
			criterias.add(Criteria.where("quantBanheiro").gte(Long.valueOf(quantBanheiros)));
		}
		
		if ( quantGaragem != null ) {
			criterias.add(Criteria.where("quantGaragem").gte(Long.valueOf(quantGaragem)));
		}
		
		if ( quantSuite != null ) {
			criterias.add(Criteria.where("quantSuite").gte(Long.valueOf(quantSuite)));
		}
		
		if ( quantSuite != null ) {
			criterias.add(Criteria.where("quantSuite").gte(Long.valueOf(quantSuite)));
		}
		
		if ( areaMin != null ) {
			criterias.add(Criteria.where("area").gte(Long.valueOf(areaMin)));
		}
		
		if ( areaMax != null ) {
			criterias.add(Criteria.where("area").lte(Long.valueOf(areaMax)));
		}

		Criteria criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
		
		Query query = new Query(criteria);	
		PageRequest pageable = PageRequest.of(page, size,  Sort.by("updatedDate").descending());	
		query.with(pageable);
		
		return mongoTemplate.find(query,Imovel.class);
	}



	@Override
	public <S extends Imovel> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Imovel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Imovel> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Imovel> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Imovel> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Imovel> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Imovel> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Page<Imovel> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Imovel> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Optional<Imovel> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Iterable<Imovel> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void delete(Imovel entity) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteAll(Iterable<? extends Imovel> entities) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public <S extends Imovel> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Imovel> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <S extends Imovel> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public <S extends Imovel> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}



	
}
