package com.example.jwtsecurity.auth.jwt.token;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TokenMetadataTest extends TokenTestFixture {

  @Test
  @DisplayName("클레임이 있는 TokenMetadata 생성 시 isExistClaim은 true를 반환한다")
  void shouldReturnTrueForIsExistClaimWhenCreatedWithClaim() {
    when(tokenProperties.getAccessTokenClaim()).thenReturn("testClaim");

    TokenMetadata result = TokenMetadata.createWithClaim(getTestUser(),
      tokenProperties);

    assertThat(result.isExistClaim()).isTrue();
  }

  @Test
  @DisplayName("클레임이 없는 TokenMetadata 생성 시 isExistClaim은 false를 반환한다")
  void shouldReturnFalseForIsExistClaimWhenCreatedWithoutClaim() {
    TokenMetadata result = TokenMetadata.createWithOutClaim(getTestUser(),
      tokenProperties);

    assertThat(result.isExistClaim()).isFalse();
  }


  @Test
  @DisplayName("현재시간에 만료시간을 더한 날짜를 DATE 타입으로 정확히 반환한다")
  void shouldReturnCorrectDateTypeForExpirationTime() {
    TokenMetadata tokenMetadata = createTokenMetadata(expirationTime);
    LocalDateTime now = LocalDateTime.now();

    Date expect = Date.from(
      now.plusNanos(tokenMetadata.expirationTime() * expirationTime).atZone(ZoneId.systemDefault())
        .toInstant());
    Date result = tokenMetadata.getExpiresAtOfDateType(now);

    assertThat(result).isEqualTo(expect);
  }


}
