package br.com.supermarket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.supermarket.dto.Item;

/**
 * The Interface ItemRepository.
 */
public interface ItemRepository extends MongoRepository<Item, String> {

}
