package io.keede.travely.core.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
* @author keede
* Created on 2023/12/10
*/
@Slf4j
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(
            final ServletRequest request,
            final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {

        log.info("Execute JWT Filter");

        /*
            1. 헤더의 값을 찾는다.
           2. 헤더에서 Authorization 의 값을 가져온다.
           3. Authorization 값(token)을 검증한다.
           4. 예외가 발생하지않으면 요청을 진행한다.
         */

        chain.doFilter(request, response);

    }
}
