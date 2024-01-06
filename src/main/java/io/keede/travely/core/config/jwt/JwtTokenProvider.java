package io.keede.travely.core.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Map;

/**
* @author keede
* Created on 2023/12/10
*/
@Slf4j
@Component
public final class JwtTokenProvider implements InitializingBean {

    private final String secret;
    private final long tokenValidityInMilliseconds;
    private SecretKey key;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000; // 토큰 만료시간에 사용,
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createJwtToken() {
        return Jwts.builder()
                .signWith(this.key, Jwts.SIG.HS256)
                .header().add("app", "travely")
                .and()
                .claims(Map.of("sub", "테스터"))
                .compact();
    }

    public Jws<Claims> bindAuthorizationToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(this.key)
                    .build()
                    .parseSignedClaims(token);
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
