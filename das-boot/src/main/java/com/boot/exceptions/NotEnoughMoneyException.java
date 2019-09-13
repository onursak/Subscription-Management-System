package com.boot.exceptions;

public class NotEnoughMoneyException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5041467146691218112L;

	public NotEnoughMoneyException() {
		super("There is no enoguh money to charge!");
		
	}
	
	public NotEnoughMoneyException(String message) {
		super(message);
	}
}
