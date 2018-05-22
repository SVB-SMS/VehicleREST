/*
 * Used to express validation exception during API REST call.
 * */
package com.mitchell.vehicle.rest.exception;

import java.util.List;

public class ValidationException extends RuntimeException{

	private final String message;
	private final String object;
	private final Object rejectedValue;
	private final List<String> validationErrors;

	public ValidationException(String message, String object, Object rejectedValue, List<String> validationErrors){
		this.message = message;
		this.object = object;
		this.rejectedValue = rejectedValue;
		this.validationErrors = validationErrors;
	}
	
	public String getMessage() {
		return message;
	}

	public String getObject() {
		return object;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}
	
	public List<String> getValidationErrors() {
		return validationErrors;
	}
}
