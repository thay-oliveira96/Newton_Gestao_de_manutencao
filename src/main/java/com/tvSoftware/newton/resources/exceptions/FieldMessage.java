package com.tvSoftware.newton.resources.exceptions;

import java.io.Serializable;

//Essa classe recupera a exeção de erro no momento que usuario nao digita nome e CPF
public class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fieldName;

	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	private String message;

	public FieldMessage() {
		super();

	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
