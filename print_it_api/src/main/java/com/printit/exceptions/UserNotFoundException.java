package com.printit.exceptions;

public class UserNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5850246990003179811L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
