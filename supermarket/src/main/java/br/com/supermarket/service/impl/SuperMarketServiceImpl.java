package br.com.supermarket.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.supermarket.dto.SuperMarket;
import br.com.supermarket.exception.CategoryBadRequestException;
import br.com.supermarket.repository.SuperMarketRepository;
import br.com.supermarket.service.SuperMarketService;
import br.com.supermarket.service.Validation;

/**
 * The Class SuperMarketServiceImpl.
 */
@Service
public class SuperMarketServiceImpl implements SuperMarketService ,Validation<SuperMarket> {

	/** The repository. */
	@Autowired
	SuperMarketRepository repository;

	@Override
	public SuperMarket create(SuperMarket superMarket) {
		return repository.save(superMarket);
	}

	@Override
	public List<SuperMarket> findAll() {
		return repository.findAll();
	}

	@Override
	public void remove(String id) {
		repository.deleteById(id);
	}

	@Override
	public SuperMarket update(SuperMarket superMarket) {		
		return repository.save(superMarket);
	}

	@Override
	public SuperMarket findById(String id) {
		Optional<SuperMarket> purchase = repository.findById(id);
		return (purchase.isPresent())?purchase.get():null;
	}

	@Override
	public void createRequestValidation(SuperMarket superMarket) {
		if(StringUtils.isEmpty(superMarket.getName())){
			throw new CategoryBadRequestException("name is null or empry!");
		} else if(StringUtils.isEmpty(superMarket.getCountry())){
			throw new CategoryBadRequestException("country is null or empry!");
		}else if(StringUtils.isEmpty(superMarket.getImage())){
			throw new CategoryBadRequestException("image is null or empry!");
		}
	}

}
