package com.example.jwtsecurity.security;

import java.util.List;

public class PermitUrls {

  private static final List<String> permitUrls = List.of("/login", "/user/check-duplicate", "/user/re-issue");

  public static boolean isPermitted (String url) {
	return permitUrls.contains(url);
  }

}
