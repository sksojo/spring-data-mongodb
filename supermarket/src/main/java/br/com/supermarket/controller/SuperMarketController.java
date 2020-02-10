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

import br.com.supermarket.dto.SuperMarket;
import br.com.supermarket.service.SuperMarketService;

/**
 * The Class SuperMarketController.
 */
@RestController
@RequestMapping("/system")
public class SuperMarketController {

	/** The service. */
	@Autowired
	SuperMarketService service;

	@GetMapping(value = "/supermarket", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SuperMarket>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/supermarket/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperMarket> findById(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping(value = "/supermarket", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperMarket> create(@RequestBody SuperMarket superMarket) {
		return ResponseEntity.ok(service.create(superMarket));
	}

	@PutMapping(value = "/supermarket", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuperMarket> update(@RequestBody SuperMarket superMarket) {
		return ResponseEntity.ok(service.update(superMarket));
	}

	@DeleteMapping(value = "/supermarket/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> remove(@PathVariable("id") String id) {
		service.remove(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
