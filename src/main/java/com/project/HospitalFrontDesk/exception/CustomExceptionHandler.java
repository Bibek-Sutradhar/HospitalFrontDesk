package com.project.HospitalFrontDesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessServiceException.class)
	public final ResponseEntity<Object> hospitalNameNotFound(BusinessServiceException ex, WebRequest request){
		ErrorResponse error = new ErrorResponse("Hospital not found", ex.getLocalizedMessage());
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidSpecialistException.class)
	public final ResponseEntity<Object> invalidSpecialistType(InvalidSpecialistException ex, WebRequest request){
		ErrorResponse error = new ErrorResponse("Hospital not found", ex.getLocalizedMessage());
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
}
