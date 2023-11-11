package com.example.jwtsecurity.auth.jwt.token;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import com.example.jwtsecurity.auth.AuthenticationAble;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TokenMetadataTest {


        @Mock
        TokenProperties tokenProperties;

        TestUser testUser;

        long expirationTime = 1000000L;

        @BeforeEach
        void setUp() {

                testUser = new TestUser();
        }

        @Test
        @DisplayName("클레임이 있는 TokenMetadata 생성 시 isExistClaim은 true를 반환한다.")
        void shouldReturnTrueForIsExistClaimWhenCreatedWithClaim() {

                when(tokenProperties.getAccessTokenClaim()).thenReturn("testClaim");

                TokenMetadata actual = TokenMetadata.createWithClaim(testUser,
                    tokenProperties);

                assertThat(actual.isExistClaim()).isTrue();
        }

        @Test
        @DisplayName("클레임이 없는 TokenMetadata 생성 시 isExistClaim은 false를 반환한다.")
        void shouldReturnFalseForIsExistClaimWhenCreatedWithoutClaim() {

                TokenMetadata actual = TokenMetadata.createWithOutClaim(testUser,
                    tokenProperties);

                assertThat(actual.isExistClaim()).isFalse();
        }

        @Test
        @DisplayName("현재시간에 만료시간을 더한 날짜를 DATE 타입으로 정확히 반환한다.")
        void shouldReturnCorrectDateTypeForExpirationTime() {

                TokenMetadata tokenMetadata = TokenFixture.createTokenMetadata(expirationTime);
                LocalDateTime now = LocalDateTime.now();
                Date expect = Date.from(
                    now.plusNanos(tokenMetadata.expirationTime() * expirationTime)
                        .atZone(ZoneId.systemDefault()).toInstant());
                Date actual = tokenMetadata.getExpiresAtOfDateType(now);

                assertThat(actual).isEqualTo(expect);
        }

        class TestUser implements AuthenticationAble {

                @Override
                public String getUserKey() {

                        return "testUserKey";
                }

        }

}
