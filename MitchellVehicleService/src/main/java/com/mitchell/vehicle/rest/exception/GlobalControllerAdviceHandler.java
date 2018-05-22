/*
 * Custom exception handler applicable for all the controllers
 * */

package com.mitchell.vehicle.rest.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdviceHandler extends ResponseEntityExceptionHandler{

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdviceHandler.class);
	
	@Override
//	@ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        LOGGER.error("In handleMethodArgumentNotValid "+ex.getMessage());
        return buildExceptionResponseEntity(ApiExceptionResponse.ApiExceptionResponseBuilder.getInstance().
				withStatus(HttpStatus.BAD_REQUEST).withMessage(ex.getMessage()).withDetails("cannot convert accept inputs: "+request.getParameterValues(ex.getParameter().getParameterName())).build());
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	protected ResponseEntity<Object> handleValidation(ValidationException ex){
		LOGGER.error("In handleValidationException Bad Request ex: "+ex.getLocalizedMessage());
		ResponseEntity<Object> entity = buildExceptionResponseEntity(ApiExceptionResponse.ApiExceptionResponseBuilder.getInstance().
				withStatus(HttpStatus.BAD_REQUEST).withMessage(ex.getMessage()).withDetails(" cannot accept "+ex.getRejectedValue()).withErrorMessages(ex.getValidationErrors()).build());
		return buildExceptionResponseEntity(ApiExceptionResponse.ApiExceptionResponseBuilder.getInstance().
				withStatus(HttpStatus.BAD_REQUEST).withMessage(ex.getMessage()).withDetails(" cannot accept "+ex.getRejectedValue()).withErrorMessages(ex.getValidationErrors()).build());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex){
		LOGGER.error("In handleMethodArgumentTypeMismatchException Bad Request ex: "+ex.getLocalizedMessage());
		return buildExceptionResponseEntity(ApiExceptionResponse.ApiExceptionResponseBuilder.getInstance().
				withStatus(HttpStatus.BAD_REQUEST).withMessage(ex.getMessage()).withDetails("cannot convert "+ex.getParameter()+" to "+ex.getRequiredType()).build());
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = {IllegalArgumentException.class})
	protected ResponseEntity<Object> handleConflict(IllegalArgumentException ex){
		LOGGER.error("In handleConflictException Conflict ex: "+ex.getLocalizedMessage());
		return buildExceptionResponseEntity(ApiExceptionResponse.ApiExceptionResponseBuilder.getInstance().
				withStatus(HttpStatus.CONFLICT).withMessage(ex.getMessage()).withDetails(ex.getLocalizedMessage()).build());
	}
	
	@ExceptionHandler(value = {SQLException.class, DataAccessException.class})
	protected ResponseEntity<Object> handleDatabaseException(){
		return null;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleResourceNotFound(HttpServletRequest req, ResourceNotFoundException ex){
		LOGGER.error("In handleResourceNotFoundException NOT_FOUND ex: "+ex.getMessage());
		return buildExceptionResponseEntity(ApiExceptionResponse.ApiExceptionResponseBuilder.getInstance().
				withStatus(HttpStatus.NOT_FOUND).withMessage("Vehicle with id "+ex.getId()+" not found!").withDetails(req.getRequestURL().toString()).build());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleRuntime(RuntimeException ex){
		LOGGER.error("In handleRuntimeException Bad Request ex: "+ex.getLocalizedMessage());
		return buildExceptionResponseEntity(ApiExceptionResponse.ApiExceptionResponseBuilder.getInstance().
				withStatus(HttpStatus.BAD_REQUEST).withMessage(ex.getMessage()).withDetails(" only numeric value accepted ").build());
	}
	
	private ResponseEntity<Object> buildExceptionResponseEntity(ApiExceptionResponse exceptionResponse){
		return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
	}
	
	
}
