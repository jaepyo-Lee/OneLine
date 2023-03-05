package com.needle.oneline.src.common.auth.jwt;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtHeaderUtil {
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    public String getBearerToken(HttpServletRequest request){
        String header = request.getHeader(HEADER_AUTHORIZATION);
        if(header==null){
            return null;
        }
        String accessTokenStr = header.substring(TOKEN_PREFIX.length());
        if(accessTokenStr==null){
            return null;
        }
        return accessTokenStr;
    }
}
