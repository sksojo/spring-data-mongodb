package br.com.supermarket.exception;

public class CategoryBadRequestException extends RuntimeException {

	public CategoryBadRequestException(String message){
		super(message);
	}
	
}
