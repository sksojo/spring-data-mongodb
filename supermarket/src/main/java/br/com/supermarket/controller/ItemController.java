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

import br.com.supermarket.dto.Item;
import br.com.supermarket.service.ItemService;

/**
 * The Class ItemController.
 */
@RestController
@RequestMapping("/system")
public class ItemController {

	/** The service. */
	@Autowired
	ItemService service;

	@GetMapping(value = "/item", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Item>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> findById(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping(value = "/item", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> create(@RequestBody Item item) {
		return ResponseEntity.ok(service.create(item));
	}

	@PutMapping(value = "/item", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> update(@RequestBody Item item) {
		return ResponseEntity.ok(service.update(item));
	}

	@DeleteMapping(value = "/item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> remove(@PathVariable("id") String id) {
		service.remove(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
