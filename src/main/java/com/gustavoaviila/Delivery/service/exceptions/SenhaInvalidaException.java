package com.gustavoaviila.Delivery.service.exceptions;

public class SenhaInvalidaException extends RuntimeException {
	
    private static final long serialVersionUID = -7662918827686094486L;

	public SenhaInvalidaException() {
        super("Senha inválida");
    }
}