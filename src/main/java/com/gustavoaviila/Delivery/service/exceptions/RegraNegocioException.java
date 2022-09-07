package com.gustavoaviila.Delivery.service.exceptions;

public class RegraNegocioException extends RuntimeException {

    private static final long serialVersionUID = -6869377397932245454L;

	public RegraNegocioException(String message) {
        super(message);
    }
}
