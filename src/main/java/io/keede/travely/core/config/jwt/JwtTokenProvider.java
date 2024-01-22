package io.keede.travely.core.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.keede.travely.core.domains.user.service.adapter.UserReader;
import io.keede.travely.core.web.security.dto.LoginDto;
import io.keede.travely.core.domains.user.entity.User;
import io.keede.travely.core.domains.user.entity.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
* @author keede
* Created on 2023/12/10
*/
@Slf4j
@Component
public final class JwtTokenProvider implements InitializingBean {

    private final UserReader userReader;
    private final String secret;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private SecretKey key;

    public JwtTokenProvider(
            final UserReader userReader,
            @Value("${jwt.secret}") final String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds
    ) {
        this.userReader = userReader;
        this.secret = secret;
        this.accessTokenValidityInMilliseconds = tokenValidityInSeconds * 1000; // 토큰 만료시간에 사용,
        this.refreshTokenValidityInMilliseconds = tokenValidityInSeconds * 5000;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Token createJwtToken(
            LoginDto loginDto
    ) {

        log.info("loginDto : {}", loginDto);

        User user = this.userReader.findUserByEmail(loginDto.email());

        Date createdAt = new Date();

        Date accessTokenExpiredTime = new Date(createdAt.getTime() + this.accessTokenValidityInMilliseconds);
        Date refreshTokenExpiredTime = new Date(createdAt.getTime() + this.refreshTokenValidityInMilliseconds);

        // TODO : password 유효성 검사

        String accessToken = Jwts.builder()
                .issuedAt(createdAt)
                .signWith(this.key, Jwts.SIG.HS256)
                .issuer(user.getEmail())
                .expiration(accessTokenExpiredTime)
                .compact();

        String refreshToken = Jwts.builder()
                .issuedAt(createdAt)
                .signWith(this.key, Jwts.SIG.HS256)
                .issuer(user.getEmail())
                .expiration(refreshTokenExpiredTime)
                .compact();

        return new Token(
                accessToken,
                refreshToken
        );
    }

    public String bindAuthorizationToken(
            String token
    ) {
        try {
            return Jwts.parser()
                    .verifyWith(this.key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getIssuer();
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            e.printStackTrace();
            log.debug("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            log.debug("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            log.debug("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            log.debug("JWT 토큰이 잘못되었습니다.");
        }

        return null;
    }

}
