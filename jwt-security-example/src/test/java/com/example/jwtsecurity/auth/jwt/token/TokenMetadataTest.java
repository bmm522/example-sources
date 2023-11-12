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
  void 클레임이_있는_TokenMetadata_생성_시_isExistClaim은_true를_반환한다() {
    when(tokenProperties.getAccessTokenClaim()).thenReturn("testClaim");

    TokenMetadata actual = TokenMetadata.createWithClaim(getTestUser(),
      tokenProperties);

    assertThat(actual.isExistClaim()).isTrue();
  }

  @Test
  void 클레임이_없는_TokenMetadata_생성_시_isExistClaim은_false를_반환한다() {
    TokenMetadata actual = TokenMetadata.createWithOutClaim(getTestUser(),
      tokenProperties);

    assertThat(actual.isExistClaim()).isFalse();
  }


  @Test
  void 현재시간에_만료시간을_더한_날짜를_DATE_타입으로_정확히_반환한다() {
    TokenMetadata tokenMetadata = createTokenMetadata(expirationTime);
    LocalDateTime now = LocalDateTime.now();

    Date expect = Date.from(
      now.plusNanos(tokenMetadata.expirationTime() * expirationTime).atZone(ZoneId.systemDefault())
        .toInstant());
    Date actual = tokenMetadata.getExpiresAtOfDateType(now);

    assertThat(actual).isEqualTo(expect);
  }


}
