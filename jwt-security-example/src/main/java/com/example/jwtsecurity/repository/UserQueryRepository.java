package com.example.jwtsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwtsecurity.domain.User;

public interface UserQueryRepository extends JpaRepository<User, String> {
  Optional<User> findByUserId (String username);
}
