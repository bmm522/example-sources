package com.example.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwtsecurity.domain.User;

public interface UserCommandRepository extends JpaRepository<User, String> {
}
