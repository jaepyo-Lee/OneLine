package com.needle.oneline.src.user;

import com.needle.oneline.src.common.auth.jwt.JwtHeaderUtil;
import com.needle.oneline.src.user.repo.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${app.auth.tokenSecret}")
    private String key;
    private final UserRepository userRepository;
    private final JwtHeaderUtil jwtHeaderUtil;
    public Long findUserId(HttpServletRequest request){
        String authorization = jwtHeaderUtil.getBearerToken(request);
        String subject = Jwts.parserBuilder()
                .setSigningKey(key.getBytes())
                .build()
                .parseClaimsJws(authorization).getBody().getSubject();
        return userRepository.findBySocialId(subject).getId();
    }
}
