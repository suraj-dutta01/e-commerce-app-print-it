package com.printit.exceptions;

public class CatagoryNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4008040808740260688L;

	public CatagoryNotFoundException(String message) {
		super(message);
	}
}
