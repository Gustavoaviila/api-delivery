package com.gustavoaviila.Delivery.service.exceptions;

public class ResourceNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 2004714913965828577L;
	
	public ResourceNotFoundException (Object id) {
		super ("Resource not found. Id " + id);
	}

}
