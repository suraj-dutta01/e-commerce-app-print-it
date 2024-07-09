package com.printit.exceptions;

public class ProductNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7498595230708728356L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}
