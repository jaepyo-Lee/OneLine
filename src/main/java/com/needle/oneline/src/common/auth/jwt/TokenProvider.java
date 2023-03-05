package com.needle.oneline.src.common.auth.jwt;

import com.needle.oneline.src.common.auth.dto.AuthToken;
import com.needle.oneline.src.common.auth.exception.TokenValidFailedException;
import com.needle.oneline.src.common.enumerate.RoleType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    @Value("${app.auth.accessExpired}")
    private String accessExpiry;

//    @Value("${app.auth.refreshExpired}")
//    private Long refreshExpiry;

    private Key key;
    private static final String AUTHORITIES_KEY = "role";

    public TokenProvider(@Value("${app.auth.tokenSecret}")String secretKey){
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public AuthToken createToken(String socialId,RoleType roleType,String accessExpireTerm/*,Long refreshExpireTerm*/) {
        Date accessDate = calDate(accessExpireTerm);
//        Date refreshExpired = new Date(System.currentTimeMillis() + refreshExpireTerm);
        return new AuthToken(socialId, roleType, accessDate, key);
    }
    public Date calDate(String expire){
        return new Date(System.currentTimeMillis() + Long.parseLong(expire));
    }
    public AuthToken createUserAppToken(String id){
        return createToken(id, RoleType.USER,accessExpiry);
    }

    public AuthToken convertAuthToken(String bearerToken) {
        return new AuthToken(bearerToken,key);
    }

    public Authentication getAuthentication(AuthToken authToken) {
        if(authToken.validate()){
            Claims tokenClaims = authToken.getTokenClaims();
            Collection<? extends GrantedAuthority> authorities = Arrays.stream(new String[]{tokenClaims.get(AUTHORITIES_KEY).toString()})
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            User principal = new User(tokenClaims.getSubject(), "", authorities);
            System.out.println("ok");
            return new UsernamePasswordAuthenticationToken(principal, authToken, authorities);
        }else{
            throw new TokenValidFailedException();
        }


    }
}