package br.com.supermarket.service;

import java.util.List;

import br.com.supermarket.dto.SuperMarket;

/**
 * The Interface SuperMarketService.
 */
public interface SuperMarketService {

	SuperMarket findById(String id);

	SuperMarket create(SuperMarket superMarket);

	List<SuperMarket> findAll();

	void remove(String id);

	SuperMarket update(SuperMarket superMarket);

}
