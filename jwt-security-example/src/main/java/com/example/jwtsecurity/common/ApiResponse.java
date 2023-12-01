package com.example.jwtsecurity.common;

import org.springframework.http.HttpStatus;

import com.example.jwtsecurity.common.exception.CustomException;

public class ApiResponse<T> {

  private Integer status;
  private T body;
  private CustomException exception;

  public ApiResponse(final HttpStatus status,final T body,final CustomException exception ){
	this.status = status.value();
	this.body = body;
	this.exception = exception;
  }

  // 비 정상 요청
  public ApiResponse(final HttpStatus status, final CustomException exception) {
	this.status = status.value();
	this.exception = exception;
  }

  public ApiResponse(final HttpStatus status, final T body) {
	this.status = status.value();
	this.body = body;
  }

  public ApiResponse(final Integer status) {
	this.status = status;
  }
}
