package br.com.supermarket.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.supermarket.dto.Category;

/**
 * The Interface CategoryRepository.
 */
public interface CategoryRepository extends MongoRepository<Category, String> {

	public Category findByName(String name);

	public List<Category> findByNameRegex(String name);
	
	public Page<Category> findAll(Pageable pageable);

}
