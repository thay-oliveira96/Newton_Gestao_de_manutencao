package com.tvSoftware.newton.services.exceptions;

/*
 * 
 * Objeto não funciona 
 * Constroi uma excessão de CPF ja cadastrado no sistema
 * 
 * */
public class DataIntegrityViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegrityViolationException(String message) {
		super(message);
	}
	
	
}
