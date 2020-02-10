package br.com.supermarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ExceptionResponse(Errors.INTERNAL_SERVER_ERROR.name(), ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionResponse(Errors.BAD_REQUEST.name(), ex.getMessage()));
    }	

	@ExceptionHandler(CategoryNotFoundException.class)
	public final ResponseEntity<Object> handleOrderBadRequest(CategoryNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ExceptionResponse(Errors.NOT_FOUND.name(), ex.getMessage()));
	}
    
	@ExceptionHandler(CategoryBadRequestException.class)
	public final ResponseEntity<Object> handleOrderBadRequest(CategoryBadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionResponse(Errors.BAD_REQUEST.name(), ex.getMessage()));
	}

	@ExceptionHandler(ItemBadRequestException.class)
	public final ResponseEntity<Object> handleOrderBadRequest(ItemBadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionResponse(Errors.BAD_REQUEST.name(), ex.getMessage()));
	}

	@ExceptionHandler(SuperMarketBadRequestException.class)
	public final ResponseEntity<Object> handleOrderBadRequest(SuperMarketBadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionResponse(Errors.BAD_REQUEST.name(), ex.getMessage()));
	}

	@ExceptionHandler(PurchaseBadRequestException.class)
	public final ResponseEntity<Object> handleOrderBadRequest(PurchaseBadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionResponse(Errors.BAD_REQUEST.name(), ex.getMessage()));
	}
}