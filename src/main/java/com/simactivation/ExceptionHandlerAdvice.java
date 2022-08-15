package com.simactivation;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;


@RestControllerAdvice
public class ExceptionHandlerAdvice {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exceptionHandler(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorsMessage> handleValidationExceptions(
		  MethodArgumentNotValidException ex) {
			 ErrorsMessage error = new ErrorsMessage();
		     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		     error.setMessage(ex.getBindingResult().getAllErrors().stream()
	                                                        .map(e->e.getDefaultMessage())
		        		                                    .collect(Collectors.joining(", ")));
		     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
	@ExceptionHandler(NoSuchCustomerException.class)
	public ResponseEntity<Object> exceptionHandler1(NoSuchCustomerException ex) {
		ErrorsMessage error = new ErrorsMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@ExceptionHandler(NoSuchCustomerAddressException.class)
		public ResponseEntity<Object> exceptionHandler2(NoSuchCustomerAddressException ex) {
		ErrorsMessage error = new ErrorsMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
}