package com.printit.exceptions;

public class CartNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3092610351549660269L;

	public CartNotFoundException(String message) {
		super(message);
	}

}
