package br.com.supermarket.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * The Class Category.
 */
@Data
@Document(collection = "category")
public class Category implements Serializable {

	@Id
	private String id;

	private String name;

	private String description;

}
