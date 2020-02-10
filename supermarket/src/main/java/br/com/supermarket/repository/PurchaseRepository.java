package br.com.supermarket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.supermarket.dto.Purchase;

/**
 * The Interface PurchaseRepository.
 */
public interface PurchaseRepository extends MongoRepository<Purchase, String> {
	
}
