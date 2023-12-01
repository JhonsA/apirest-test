package com.ntt.apirest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(cstf -> cstf.disable())
            .authorizeHttpRequests(authRequest -> authRequest
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/users/**").permitAll()
                .requestMatchers("/h2-ui/**").permitAll()
                .requestMatchers("/doc/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(withDefaults())
            .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable())
            .build();
    }
    
}
