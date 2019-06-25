package com.starbus.starbus.Security.Interceptors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenApiTokenInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(OpenApiTokenInterceptor.class);
    private static final String HEADER_NAME = "x-access-token";

    @Value("${jwt.secretKey}")
    private static String secretKey;

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("in interceptor");
        String tokenStirng = request.getHeader("x-access-token");
        if (tokenStirng != null) {
            logger.info(tokenStirng);

            Claims tokenBody = getTokenBody(tokenStirng);
            if (tokenBody != null) {
                request.setAttribute("decoded", tokenBody);
                return true;
            }
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
