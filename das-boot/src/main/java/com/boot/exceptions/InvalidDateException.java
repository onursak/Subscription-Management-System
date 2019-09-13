package com.boot.exceptions;

public class InvalidDateException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1845379844160824336L;

	public InvalidDateException() {
		super("The given date information is invalid!");
	}
	
	public InvalidDateException(String message) {
		super(message);
	}
}
