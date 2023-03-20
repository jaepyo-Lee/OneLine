package com.needle.oneline.src.common.auth.dto;

import com.needle.oneline.src.common.enumerate.RoleType;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

@Slf4j
@Getter
@RequiredArgsConstructor
public class AuthToken {
    private final String accessToken;
//    private final String refreshToken;
    private final Key key;
    private static String AUTHORITIES_KEY = "role";

    public AuthToken(String socialId, RoleType roleType, Date accessExpiry,Key key){
        String role = roleType.toString();
        this.key = key;
        this.accessToken = createToken(socialId, role, accessExpiry);
//        this.refreshToken = createToken(socialId, role, refreshExpiry);
    }
    public boolean validate() {
        return (this.getTokenClaims() != null);
    }

    public String createToken(String socialId,String role,Date expiry){
        return Jwts.builder()
                .setSubject(socialId)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiry)
                .claim(AUTHORITIES_KEY, role)
                .compact();

    }

    public Claims getTokenClaims(){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        }catch (SecurityException e) {
            log.info("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
        }
        return null;
    }
}
