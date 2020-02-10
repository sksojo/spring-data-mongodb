package br.com.supermarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supermarket.dto.Purchase;
import br.com.supermarket.service.PurchaseService;

/**
 * The Class PurchaseController.
 */
@RestController
@RequestMapping("/system")
public class PurchaseController {

	/** The service. */
	@Autowired
	PurchaseService service;

	@GetMapping(value = "/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Purchase>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/purchase/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Purchase> findById(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping(value = "/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Purchase> create(@RequestBody Purchase purchase) {
		return ResponseEntity.ok(service.create(purchase));
	}

	@PutMapping(value = "/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Purchase> update(@RequestBody Purchase purchase) {
		return ResponseEntity.ok(service.update(purchase));
	}

	@DeleteMapping(value = "/purchase/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> remove(@PathVariable("id") String id) {
		service.remove(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
