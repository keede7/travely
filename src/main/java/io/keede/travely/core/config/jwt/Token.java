package io.keede.travely.core.config.jwt;

/**
* @author keede
* Created on 2024/01/07
*/
public record Token(
        String accessToken,
        String refreshToken
) {
}
