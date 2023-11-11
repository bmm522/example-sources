package com.example.jwtsecurity.auth.jwt.token;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TokenMetadataTest {

        @Test
        @DisplayName("현재시간을 넣으면 만료시간이 더해져서 DATE 타입으로 반환된다")
        void whenCurrentTimeIsGivenThenReturnDateType() {

                long expirationTime = 1000000L;
                TokenMetadata tokenMetadata = TokenFixture.createTokenMetadata(expirationTime);
                LocalDateTime now = LocalDateTime.now();
                Date expect = Date.from(
                    now.plusNanos(tokenMetadata.expirationTime() * expirationTime)
                        .atZone(ZoneId.systemDefault()).toInstant());
                Date actual = tokenMetadata.getExpiresAtOfDateType(now);

                assertThat(actual).isEqualTo(expect);
        }

}
