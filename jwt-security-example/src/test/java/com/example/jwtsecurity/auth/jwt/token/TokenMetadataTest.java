package com.example.jwtsecurity.auth.jwt.token;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.example.jwtsecurity.auth.jwt.fixture.FixtureTokenMetadata;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TokenMetadataTest  {

    @Test
    @DisplayName("클레임이 있는 TokenMetadata 생성 시 isExistClaim은 true를 반환한다")
    void shouldIssueValidTokenWithMetadata() {
        TokenMetadata result = FixtureTokenMetadata.createTokenMetadata();

        assertThat(result.isExistClaim()).isTrue();
    }

    @Test
    @DisplayName("클레임이 없는 TokenMetadata 생성 시 isExistClaim은 false를 반환한다")
    void shouldReturnFalseForIsExistClaimWhenCreatedWithoutClaim() {
        TokenMetadata result =FixtureTokenMetadata.createTokenMetadataWithOutClaim();

        assertThat(result.isExistClaim()).isFalse();
    }

    @Test
    @DisplayName("만료시간에 음수가 들어오면 예외를 반환한다.")
    void returnErrorWhenInputExpiresAtOfMinus() {
        assertThatThrownBy(() -> FixtureTokenMetadata.createTokenMetadataWithExpirationAt(-1L))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("현재시간에 만료시간을 더한 날짜를 DATE 타입으로 정확히 반환한다")
    void shouldReturnCorrectDateTypeForExpirationTime() {
        TokenMetadata tokenMetadata =  FixtureTokenMetadata.createTokenMetadata();
        LocalDateTime now = LocalDateTime.now();

        Date expect = Date.from(
            now.plusNanos(tokenMetadata.expirationTime() * FixtureTokenMetadata.EXPIRATION_AT)
                .atZone(ZoneId.systemDefault())
                .toInstant());
        Date result = tokenMetadata.getExpiresAtOfDateType(now);

        assertThat(result).isEqualTo(expect);
    }


}
