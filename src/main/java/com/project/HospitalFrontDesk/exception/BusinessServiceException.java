package com.project.HospitalFrontDesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BusinessServiceException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public BusinessServiceException(String exception) {
		super(exception);
		System.out.println("in BusinessServiceException");
	}
}
