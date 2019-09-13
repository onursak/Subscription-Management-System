package com.boot.exceptions;

public class PeriodNotValidException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1619672331165677114L;
	
	public PeriodNotValidException() {
		super("Period values not in valid form! Charging period cannot be higher than subscription period.");
	}
	
	public PeriodNotValidException(String message) {
		super(message);
	}
	
}
