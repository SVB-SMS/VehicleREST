package com.mitchell.vehicle.rest.exception;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.http.HttpStatus;

@XmlRootElement
public class ApiExceptionResponse {
	
	private HttpStatus status;
	private String errorCode;
	private String message;
	private String details;
	private List<String> errorMessages;
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}	
	public List<String> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	//Builder
	public static final class ApiExceptionResponseBuilder{
		private HttpStatus status;
		private String errorCode;
		private String message;
		private String details;
		private List<String> errorMessages;
		
		private ApiExceptionResponseBuilder(){
			
		}
		
		public static ApiExceptionResponseBuilder getInstance(){
			return new ApiExceptionResponseBuilder();
		}
		
		public ApiExceptionResponseBuilder withStatus(HttpStatus status){
			this.status = status;
			return this;
		}
		
		public ApiExceptionResponseBuilder withErrorCode(String errorCode){
			this.errorCode = errorCode;
			return this;
		}
		
		public ApiExceptionResponseBuilder withMessage(String message){
			this.message = message;
			return this;
		}
		
		public ApiExceptionResponseBuilder withDetails(String details){
			this.details = details;
			return this;
		}
		
		public ApiExceptionResponseBuilder withErrorMessages(List<String> errorMessages){
			this.errorMessages = errorMessages;
			return this;
		}
		
		public ApiExceptionResponse build(){
			ApiExceptionResponse exceptionResponse = new ApiExceptionResponse();
			exceptionResponse.status = this.status;
			exceptionResponse.errorCode = this.errorCode;
			exceptionResponse.message = this.message;
			exceptionResponse.details = this.details;
			exceptionResponse.errorMessages = this.errorMessages;				
			return exceptionResponse;
		}
	}	
}
