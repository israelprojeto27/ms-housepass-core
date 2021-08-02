package com.housepass.message.app.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@Override 
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseException response = new ResponseException(null, "erro",HttpStatus.BAD_REQUEST , "Erro na validação", ex.getBindingResult().getFieldErrors());	        
        return handleExceptionInternal(ex, response, headers, response.getStatus(), request);
    }
	  
	
	@ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseException> handleNotFoundException(DataNotFoundException ex, WebRequest request) {
              
		ResponseException response = new ResponseException(null, "erro",HttpStatus.NOT_FOUND , ex.getMessage(), null);
        return new ResponseEntity<ResponseException>(response, response.getStatus());
    }
	  
}
