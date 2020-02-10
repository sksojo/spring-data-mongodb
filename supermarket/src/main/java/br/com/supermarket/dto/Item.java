package br.com.supermarket.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * The Class Item.
 */
@Data
@Document(collection = "item")
public class Item implements Serializable {

	@Id
	private String id;

	private String name;

	private Category category;

	private String brand;

	private float price;
	
}
