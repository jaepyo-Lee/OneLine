/*
package com.needle.oneline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class WebSecurityConfig{
    private static final String[] AUTH_WHITE_LIST =
            {
                    "/swagger-ui/**",
                    "/h2-console/**",
                    "login/**"
            };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITE_LIST).permitAll()
                .anyRequest().permitAll();
        return http.build();


    }
}
*/
