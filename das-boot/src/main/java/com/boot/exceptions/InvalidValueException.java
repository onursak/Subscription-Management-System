package com.boot.exceptions;

public class InvalidValueException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3299487990506012354L;

	public InvalidValueException() {
		super("You entered invalid value!");
	}
	
	public InvalidValueException(String message) {
		super(message);
	}
}
