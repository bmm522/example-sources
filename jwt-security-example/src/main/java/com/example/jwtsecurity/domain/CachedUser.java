package com.example.jwtsecurity.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RedisHash(value = "cached_user")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CachedUser {

  @Id
  private String id;

  @Indexed
  private String userKey;

  private String refreshToken;

  @TimeToLive
  private long ttl;
}
