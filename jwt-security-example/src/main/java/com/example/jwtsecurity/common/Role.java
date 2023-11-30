package com.example.jwtsecurity.common;

public enum Role {

  GUEST("GUEST"), USER("USER");

  private final String value;

  Role (final String value) {
	this.value = value;
  }
}
