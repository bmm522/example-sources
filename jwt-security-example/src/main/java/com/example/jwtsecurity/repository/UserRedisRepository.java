package com.example.jwtsecurity.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.jwtsecurity.domain.CachedUser;

public interface UserRedisRepository extends CrudRepository<CachedUser, String> {
}
