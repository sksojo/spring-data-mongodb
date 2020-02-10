package br.com.supermarket;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.ClassPathResource;

import br.com.supermarket.dto.Category;
import br.com.supermarket.dto.Item;
import br.com.supermarket.dto.Purchase;
import br.com.supermarket.dto.SuperMarket;

public abstract class BaseMockTest {

	protected Item getItem() {
		Item item = new Item();
		item.setId("5ced2efe49ae7645f483ad8a");
		item.setName("item name");
		item.setBrand("brand name");
		item.setCategory(getCategory());
		item.setPrice(2.5f);
		return item;
	}
	
	protected Category getCategory() {
		Category category = new Category();
		category.setId("5ced2efe49ae7645f483ad8b");
		category.setName("category name");
		category.setDescription("category informaction");
		return category;
	}
	
	protected SuperMarket getSuperMarket() {
		SuperMarket category = new SuperMarket();
		category.setId("5ced2efe49ae7645f483ad8c");
		category.setName("category name");
		category.setImage("https://upload.wikimedia.org/wikipedia/pt/4/43/Mario_Bros_front.jpg");
		category.setCountry("Brazil");
		return category;
	}
	
	protected Purchase getPurchase() {
		Purchase category = new Purchase();
		category.setId("5ced2efe49ae7645f483ad8d");
		category.setDate(ZonedDateTime.now());
		category.setItems(Stream.of(getItem()).collect(Collectors.toList()));
		category.setSuperMarket(getSuperMarket());
		category.setTotalPrice(2.5f);
		return category;
	}
	
	protected String generatePayload(String path) {
		String payload = null;
		try {
			File file = new ClassPathResource(path).getFile();
			payload = new String(Files.readAllBytes(Paths.get(file.getPath())));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return payload;
	}
}
