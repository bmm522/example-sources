package com.example.jwtsecurity.domain;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.jwtsecurity.common.Role;
import com.example.jwtsecurity.security.AuthenticationAble;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity implements AuthenticationAble, UserDetails, OAuth2User {

  public static User of (final String userId, final String email, final Role role) {
	return new User(userId, email, role);
  }

  @Id
  private String userId;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  @Override
  public String getUserKey () {
	return userId;
  }

  @Override
  public boolean equals (Object o) {
	if (this == o)
	  return true;
	if (!(o instanceof User user))
	  return false;
	return Objects.equals(userId, user.userId);
  }

  @Override
  public int hashCode () {
	return Objects.hash(userId);
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities () {
	return null;
  }

  @Override
  public String getPassword () {
	return null;
  }

  @Override
  public String getUsername () {
	return userId;
  }

  @Override
  public boolean isAccountNonExpired () {
	return true;
  }

  @Override
  public boolean isAccountNonLocked () {
	return true;
  }

  @Override
  public boolean isCredentialsNonExpired () {
	return true;
  }

  @Override
  public boolean isEnabled () {
	return true;
  }

  @Override
  public String getName () {
	return null;
  }

  @Override
  public Map<String, Object> getAttributes () {
	return null;
  }
}
