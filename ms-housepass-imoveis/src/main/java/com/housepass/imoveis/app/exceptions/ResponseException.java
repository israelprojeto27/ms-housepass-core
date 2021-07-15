package com.housepass.imoveis.app.exceptions;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class ResponseException {
	
	public ResponseException() {
		super();		
	}

	private HttpStatus status;
	private String tipo;
	private String message;
	
	/*@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDateTime dateResponse;*/
	  
	private Object entity;
	private List<ResponseException> errors = null;	
	

	public ResponseException(Object obj, String tipo, HttpStatus status, String mensagem, List<FieldError> list) {
		this.entity = obj;
		this.tipo = tipo;
		this.status = status;
		this.message = mensagem;	
	}

}