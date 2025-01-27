package com.example.sileo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf
                .ignoringRequestMatchers("/usuarios")) // Ignorar requisições para o endpoint de criação de usuários)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll() // Permitir acesso ao endpoint de login
                        .anyRequest().authenticated()) // Requer autenticação para qualquer outra requisição
                        .formLogin(Customizer.withDefaults()) // Habilitar login baseado em formulário
                        .oauth2ResourceServer((oauth2) -> oauth2
                                .jwt(Customizer.withDefaults())
                        );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
