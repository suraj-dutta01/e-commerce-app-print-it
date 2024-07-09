package com.printit.exceptions;

public class OrderNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8084795511941159566L;

	public OrderNotFoundException(String message) {
		super(message);
	}

}
