package br.com.supermarket.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * The Class Purchase.
 */
@Data
@Document(collection = "purchase")
public class Purchase implements Serializable {

	@Id
	private String id;

	private ZonedDateTime date;

	private SuperMarket superMarket;

	private float totalPrice;

	private List<Item> items;	
	
}
