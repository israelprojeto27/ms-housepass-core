package com.housepass.imoveis.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException{	
 
  private static final long serialVersionUID = -6911795779932687314L;

  public DataNotFoundException(String message) {
    super(message);
  }

}
