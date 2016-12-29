package com.lovi.searchbox.exception;

public class SearchBoxOperationsException extends RuntimeException{

	private static final long serialVersionUID = -2060823217051321631L;

	public SearchBoxOperationsException() {
	}
	
	public SearchBoxOperationsException(Throwable throwable) {
		super(throwable);
	}
	
	public SearchBoxOperationsException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
