package com.gustavoaviila.Delivery.service.exceptions;

public class DatabaseException extends RuntimeException{

	private static final long serialVersionUID = 1580552697403527597L;

	public DatabaseException (String msg) {
		super(msg);
	}
}
