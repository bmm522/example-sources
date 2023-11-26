package com.example.jwtsecurity.security;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.jwtsecurity.security.cookie.CookieParser;
import com.example.jwtsecurity.security.jwt.fixture.FixtureToken;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class CookieParserTest {


    @Test
    @DisplayName("해당 이름의 쿠키를 반환한다.")
    void returnCookieMatchedName() {
        String accessTokenValue = FixtureToken.createAccessToken().getValue();

        Cookie[] given = {
            new Cookie("AccessToken", accessTokenValue)
        };

        String result = CookieParser.getValue(given, "AccessToken");

        assertThat(result).isEqualTo(accessTokenValue);
    }

    @Test
    @DisplayName("해당 이름에 맞는 쿠키가 없으면 null을 반환한다.")
    void returnNullStringWhenNotMatchedName() {
        String accessTokenValue = FixtureToken.createAccessToken().getValue();

        Cookie[] given = {
            new Cookie("AccessToken", accessTokenValue)
        };

        String result = CookieParser.getValue(given, "asdfsadfsadf");

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("쿠키 배열에 null이 들어오면 null을 반환한다.")
    void returnNullWhenInputNullCookieArray() {
        String result = CookieParser.getValue(null, "AccessToken");

        assertThat(result).isNull();
    }

}
