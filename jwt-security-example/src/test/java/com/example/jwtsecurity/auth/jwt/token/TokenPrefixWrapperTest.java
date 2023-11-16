package com.example.jwtsecurity.auth.jwt.token;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.jwtsecurity.auth.jwt.token.fixture.FixtureToken;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class TokenPrefixWrapperTest {

    @ParameterizedTest(name = "prefix:{1}")
    @MethodSource("provideParameters")
    @DisplayName("생성 시에 prefix가 붙은 Token을 내보낸다")
    void plusPrefixWhenGenerate(Token token, String prefix) {
        Token result = TokenPrefixWrapper.of(prefix, token);

        assertThat(result.getValue().startsWith(prefix)).isTrue();
    }


    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(FixtureToken.createAccessToken(), "Bearer "),
            Arguments.of(FixtureToken.createRefreshToken(), "RefreshToken ")
        );
    }

}
