package br.com.supermarket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.supermarket.dto.SuperMarket;

/**
 * The Interface SuperMarketRepository.
 */
public interface SuperMarketRepository extends MongoRepository<SuperMarket, String> {

}
