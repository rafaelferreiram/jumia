package com.jumia.exercise.exception;

public class CustomerFilterException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CustomerFilterException(String msg) {
		super(msg);
	}
}
