package br.com.supermarket.service;

import java.util.List;

import br.com.supermarket.dto.Purchase;

/**
 * The Interface PurchaseService.
 */
public interface PurchaseService {

	Purchase findById(String id);

	Purchase create(Purchase purchase);

	List<Purchase> findAll();

	void remove(String id);

	Purchase update(Purchase purchase);

}
