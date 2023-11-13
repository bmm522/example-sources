package com.example.jwtsecurity.auth.jwt.token;

import com.example.jwtsecurity.auth.AuthenticationAble;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TokenMetadatas {

    TokenMetadata accessTokenMetadata;

    TokenMetadata refreshTokenMetadata;


    public static TokenMetadatas of(AuthenticationAble authenticationAble,
        TokenProperties tokenProperties) {
        return new TokenMetadatas(
            createAccessTokenMetadata(authenticationAble, tokenProperties)
            , createRefreshTokenMetadata(authenticationAble, tokenProperties));
    }

    public static TokenMetadata createAccessTokenMetadata(AuthenticationAble authenticationAble,
        TokenProperties tokenProperties) {
        return TokenMetadata.of(authenticationAble.getUserKey(),
            tokenProperties.getSecretKey(),
            tokenProperties.getAccessTokenSubject(),
            tokenProperties.getAccessTokenExpirationTime(),
            tokenProperties.getAccessTokenPrefix(),
            tokenProperties.getAccessTokenClaim());
    }

    public static TokenMetadata createRefreshTokenMetadata(AuthenticationAble authenticationAble,
        TokenProperties tokenProperties) {
        return TokenMetadata.of(authenticationAble.getUserKey(),
            tokenProperties.getSecretKey(),
            tokenProperties.getRefreshTokenSubject(),
            tokenProperties.getRefreshTokenExpirationTime(),
            tokenProperties.getRefreshTokenPrefix(),
            null);
    }


}
