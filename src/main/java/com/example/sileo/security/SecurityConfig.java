package com.example.sileo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/usuarios/login", //url que usaremos para fazer login
            "/usuarios" //url que usaremos para criar um usuário
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll() // Allow access to the login endpoint
                        .anyRequest().authenticated()) // Require authentication for any other request
                .formLogin(Customizer.withDefaults()) // Enable form-based login
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
