package br.com.supermarket.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import br.com.supermarket.dto.Purchase;
import br.com.supermarket.exception.CategoryBadRequestException;
import br.com.supermarket.repository.PurchaseRepository;
import br.com.supermarket.service.PurchaseService;
import br.com.supermarket.service.Validation;

/**
 * The Class PurchaseServiceImpl.
 */
@Service
public class PurchaseServiceImpl implements PurchaseService, Validation<Purchase> {

	/** The repository. */
	@Autowired
	PurchaseRepository repository;

	@Override
	public Purchase create(Purchase item) {
		return repository.save(item);
	}

	@Override
	public List<Purchase> findAll() {
		return repository.findAll();
	}

	@Override
	public void remove(String id) {
		repository.deleteById(id);
	}

	@Override
	public Purchase update(Purchase item) {		
		return repository.save(item);
	}

	@Override
	public Purchase findById(String id) {
		Optional<Purchase> purchase = repository.findById(id);
		return (purchase.isPresent())?purchase.get():null;
	}

	@Override
	public void createRequestValidation(Purchase purchase) {
		if(StringUtils.isEmpty(purchase.getDate())){
			throw new CategoryBadRequestException("date is null or empry!");
		} else if(CollectionUtils.isEmpty(purchase.getItems())){
			throw new CategoryBadRequestException("items is null or empry!");
		}else if(purchase.getSuperMarket() == null || 
				StringUtils.isEmpty(purchase.getSuperMarket().getId()) ||
				StringUtils.isEmpty(purchase.getSuperMarket().getName()) ||
				StringUtils.isEmpty(purchase.getSuperMarket().getCountry())){
			throw new CategoryBadRequestException("super market is null or empry!");
		}else if(purchase.getTotalPrice() < 0){
			throw new CategoryBadRequestException("total price is null or empry!");
		}
	}

}
