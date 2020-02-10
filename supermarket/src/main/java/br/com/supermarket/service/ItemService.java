package br.com.supermarket.service;

import java.util.List;

import br.com.supermarket.dto.Item;

/**
 * The Interface ItemService.
 */
public interface ItemService {

	Item findById(String id);

	Item create(Item item);

	List<Item> findAll();

	void remove(String id);

	Item update(Item item);

}
