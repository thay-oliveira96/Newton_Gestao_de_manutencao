package com.tvSoftware.newton.services.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.tvSoftware.newton.resources.exceptions.FieldMessage;
import com.tvSoftware.newton.resources.exceptions.StandardError;
/*
 * 
 * Classe que auxilia na manipulação de exeção no caso exeção onde o usuario esquece de colocar o nome, email, 
 * senaha e etc.
 * 
 * */
public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

}
