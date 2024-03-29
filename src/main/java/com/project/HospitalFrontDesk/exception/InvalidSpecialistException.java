package com.project.HospitalFrontDesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidSpecialistException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidSpecialistException(String specialistType) {
		super("Specialist Type "+specialistType+" not valid");
	}
}
