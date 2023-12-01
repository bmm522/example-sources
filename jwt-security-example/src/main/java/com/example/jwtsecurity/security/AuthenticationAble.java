package com.example.jwtsecurity.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface AuthenticationAble extends UserDetails, OAuth2User {

  String getUserId ();


}
