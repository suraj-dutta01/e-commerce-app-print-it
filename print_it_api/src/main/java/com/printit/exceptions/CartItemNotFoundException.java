package com.printit.exceptions;

public class CartItemNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6381134776418983255L;

	public CartItemNotFoundException(String message) {
		super(message);
	}

}
