package com.printit.exceptions;

public class AddressNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6654310103369684628L;

	public AddressNotFoundException(String message) {
		super(message);
	}
}
