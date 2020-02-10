package br.com.supermarket.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.supermarket.dto.Category;
import br.com.supermarket.exception.CategoryBadRequestException;
import br.com.supermarket.repository.CategoryRepository;
import br.com.supermarket.service.CategoryService;
import br.com.supermarket.service.Validation;

/**
 * The Class CategoryServiceImpl.
 */
@Service
public class CategoryServiceImpl implements CategoryService, Validation<Category> {

	/** The category repository. */
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category create(Category category) {
		createRequestValidation(category);
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public void remove(String id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public List<Category> findByNameRegex(String name) {
		return categoryRepository.findByNameRegex(".*" + name + ".*");
	}

	@Override
	public Category update(String id, Category category) {
		if(!categoryRepository.findById(id).isPresent()){
			
		}
		return categoryRepository.save(category);
	}

	@Override
	public Category findById(String id) {
		Optional<Category> categoria = categoryRepository.findById(id);
		return (categoria.isPresent()) ? categoria.get() : null;
	}

	@Override
	public List<Category> findAllPageable(int skip, int top) {
		final Pageable page = PageRequest.of(skip, top);
		Page<Category> result = categoryRepository.findAll(page);
		return (result.getPageable() != null) ? result.getContent() : null;
	}

	@Override
	public void createRequestValidation(Category category) {
		if(StringUtils.isEmpty(category.getName())){
			throw new CategoryBadRequestException("name is null or empry!");
		} else if(StringUtils.isEmpty(category.getDescription())){
			throw new CategoryBadRequestException("description is null or empry!");
		}
	}

}
