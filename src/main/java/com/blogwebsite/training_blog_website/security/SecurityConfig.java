package com.blogwebsite.training_blog_website.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.blogwebsite.training_blog_website.security.JwtUtils;
import org.springframework.http.HttpMethod;
@Configuration
public class SecurityConfig {

    private final JwtUtils jwtUtil;

    public SecurityConfig(JwtUtils jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.csrf().disable().cors().and().authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/users/login", "/api/users/signup").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/blogs/**").permitAll()
            .anyRequest().authenticated()
        )
            .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
