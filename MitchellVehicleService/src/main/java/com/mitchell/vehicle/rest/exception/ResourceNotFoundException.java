package com.mitchell.vehicle.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionId = -2859292084648724403L;
	private int id;
//	private String message;

	public ResourceNotFoundException(){
		
	}

	public ResourceNotFoundException(int id){
//		super();
		this.id = id;
//		this.message = message;
	}
	
	public int getId() {
		return id;
	}
}
