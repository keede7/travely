package io.keede.travely.core.web.security;


import io.keede.travely.core.config.jwt.JwtTokenProvider;
import io.keede.travely.core.config.jwt.Token;
import io.keede.travely.core.web.security.dto.LoginDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author keede
* Created on 2024/01/06
*/
@RestController
@RequestMapping("/auth")
public class AuthApiController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthApiController(
            final JwtTokenProvider jwtTokenProvider
    ) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public Token login(
            @RequestBody LoginDto loginDto
    ) {
        return this.jwtTokenProvider.createJwtToken(loginDto);
    }

}

