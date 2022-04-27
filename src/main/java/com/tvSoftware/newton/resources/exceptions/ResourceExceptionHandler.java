package com.tvSoftware.newton.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tvSoftware.newton.services.exceptions.DataIntegrityViolationException;
import com.tvSoftware.newton.services.exceptions.ObjectnotFoundException;
import com.tvSoftware.newton.services.exceptions.ValidationError;
/*
 * 
 * Manipulador de exeções para os recursos
 * 
 * 
 * */
@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectnotFoundException.class)
	public ResponseEntity<StandardError> objectnotFoundException(ObjectnotFoundException ex, 
			HttpServletRequest request) {
		
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"object Not Found", ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	//Exeção de CPF ja cadastrado no sistema
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, 
			HttpServletRequest request) {
		
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Violação de Dados", ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	//Exeção de CPF ja cadastrado no sistema
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex, 
				HttpServletRequest request) {
			
		ValidationError errors =  new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Validation error", "Erro na Validação dos Campos", request.getRequestURI());
			for(FieldError x : ex.getBindingResult().getFieldErrors()) {
				errors.addError(x.getField(), x.getDefaultMessage());
				
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
}
