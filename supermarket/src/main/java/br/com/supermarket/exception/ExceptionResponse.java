package br.com.supermarket.exception;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class ExceptionResponse implements Serializable {

	private String code;
	private List<String> messages;

	public ExceptionResponse(String code, String details) {
		this.code = code;
		this.messages = Collections.singletonList(details);
	}

	public ExceptionResponse(String code, List<String> details) {
		this.code = code;
		this.messages = details;
	}

	public ExceptionResponse(String code) {
		this.code = code;
	}



}
