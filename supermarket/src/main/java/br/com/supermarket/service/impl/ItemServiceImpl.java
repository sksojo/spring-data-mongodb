package br.com.supermarket.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.supermarket.dto.Item;
import br.com.supermarket.exception.CategoryBadRequestException;
import br.com.supermarket.repository.ItemRepository;
import br.com.supermarket.service.ItemService;
import br.com.supermarket.service.Validation;

/**
 * The Class ItemServiceImpl.
 */
@Service
public class ItemServiceImpl implements ItemService, Validation<Item> {
	
	/** The repository. */
	@Autowired
	ItemRepository repository;

	@Override
	public Item create(Item item) {
		return repository.save(item);
	}

	@Override
	public List<Item> findAll() {
		return repository.findAll();
	}

	@Override
	public void remove(String id) {
		repository.deleteById(id);
	}

	@Override
	public Item update(Item item) {		
		return repository.save(item);
	}

	@Override
	public Item findById(String id) {
		Optional<Item> categoria = repository.findById(id);
		return (categoria.isPresent())?categoria.get():null;
	}

	@Override
	public void createRequestValidation(Item item) {
		if (StringUtils.isEmpty(item.getName())) {
			throw new CategoryBadRequestException("name is null or empry!");
		} else if (item.getCategory() == null || StringUtils.isEmpty(item.getCategory().getId()) || StringUtils.isEmpty(item.getCategory().getName())) {
			throw new CategoryBadRequestException("category is null or empry!");
		} else if (StringUtils.isEmpty(item.getBrand())) {
			throw new CategoryBadRequestException("brand is null or empry!");
		} else if (item.getPrice() < 0) {
			throw new CategoryBadRequestException("price is null or empry!");
		}
	}

}
