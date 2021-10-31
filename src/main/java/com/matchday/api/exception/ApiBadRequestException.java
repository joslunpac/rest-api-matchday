package com.matchday.api.exception;

public class ApiBadRequestException extends RuntimeException {

	public ApiBadRequestException(String message) {
		super(message);
	}

}
