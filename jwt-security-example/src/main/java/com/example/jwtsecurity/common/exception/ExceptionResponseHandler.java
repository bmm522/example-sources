package com.example.jwtsecurity.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.jwtsecurity.common.ApiResponse;
import com.example.jwtsecurity.util.ResponseUtils;

@RestControllerAdvice
public class ExceptionResponseHandler {

	@ExceptionHandler(UnAuthorizedException.class)
  	public ApiResponse<CustomException> handleUnAuthorized(UnAuthorizedException e) {
	  return ResponseUtils.fail(HttpStatus.UNAUTHORIZED, e);
	}
}
