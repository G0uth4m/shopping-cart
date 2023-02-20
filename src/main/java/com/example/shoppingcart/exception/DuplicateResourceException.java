package com.example.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateResourceException extends RuntimeException {

  public DuplicateResourceException(String message) {
    super(message);
  }
}
