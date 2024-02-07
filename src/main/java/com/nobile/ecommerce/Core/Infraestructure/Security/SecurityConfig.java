package com.nobile.ecommerce.Core.Infraestructure.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.nobile.ecommerce.Core.Infraestructure.Security.Filter.AuthenticationFilter;
import com.nobile.ecommerce.Core.Infraestructure.Security.Filter.ExceptionHandlerFilter;
import com.nobile.ecommerce.Core.Infraestructure.Security.Filter.JWTAuthorizationFilter;
import com.nobile.ecommerce.Core.Infraestructure.Security.Manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/user/login");

        http.headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions
                        .sameOrigin()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilterBefore(authenticationFilter, ExceptionHandlerFilter.class)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
