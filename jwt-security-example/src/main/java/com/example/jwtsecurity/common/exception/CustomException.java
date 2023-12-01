package com.example.jwtsecurity.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomException extends RuntimeException{

  private String message;

  public CustomException(final String message) {
	this.message = message;
	log.error(message);
  }
}
