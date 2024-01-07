package io.keede.travely.core.config.resolver;

import io.keede.travely.core.config.jwt.JwtTokenProvider;
import io.keede.travely.core.web.security.dto.LoginUser;
import io.keede.travely.core.web.security.dto.Session;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
* @author keede
* Created on 2024/01/07
*/
public class LoginSessionResolver implements HandlerMethodArgumentResolver {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private final JwtTokenProvider jwtTokenProvider;


    public LoginSessionResolver(
            final JwtTokenProvider jwtTokenProvider
    ) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean supportsParameter(
            final MethodParameter parameter
    ) {

        boolean hasAnnotation = parameter.hasParameterAnnotation(Session.class);
        boolean isParameterType = parameter.getParameterType().isAssignableFrom(LoginUser.class);

        return hasAnnotation && isParameterType;
    }

    @Override
    public Object resolveArgument(
            final MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory
    ) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();

        String accessToken = httpServletRequest.getHeader(HEADER_AUTHORIZATION);

        String email = jwtTokenProvider.bindAuthorizationToken(accessToken);

        return new LoginUser(
                email,
                accessToken
        );
    }
}
