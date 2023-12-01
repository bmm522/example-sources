package com.example.jwtsecurity.util;

import org.springframework.http.HttpStatus;

import com.example.jwtsecurity.common.ApiResponse;
import com.example.jwtsecurity.common.exception.CustomException;

public class ResponseUtils {

  public static <T> ApiResponse<T> success(final HttpStatus status, final T data) {
	return new ApiResponse<>(status, data);
  }

  public static ApiResponse<CustomException> fail(final HttpStatus status, final CustomException exception) {
	return new ApiResponse<>(status, exception);
  }

  public static ApiResponse fail (final Integer status) {
	return new ApiResponse(status);
  }
}
