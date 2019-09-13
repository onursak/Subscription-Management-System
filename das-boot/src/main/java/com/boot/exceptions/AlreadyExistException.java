package com.boot.exceptions;

public class AlreadyExistException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 703121455668468067L;

	/**
	 * 
	 */

	public AlreadyExistException() {
		super("The item is already in the database!");
	}
	
	public AlreadyExistException(String message) {
		super(message);
	}
}
