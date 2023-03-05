package com.needle.oneline.src.common.auth.jwt;

import com.needle.oneline.src.common.auth.dto.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtHeaderUtil jwtHeaderUtil;
    private final TokenProvider tokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) { //토큰이 있다면!
            String bearerToken = jwtHeaderUtil.getBearerToken(request);
            AuthToken authToken = tokenProvider.convertAuthToken(bearerToken);
            if(authToken.validate()){
                tokenProvider.getAuthentication(authToken);
            }
            System.out.println("일단ㅇㅋ");
        }
        filterChain.doFilter(request, response);
    }
}
