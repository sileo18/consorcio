package com.example.sileo.security;

import com.example.sileo.domain.Usuario.Usuario;
import com.example.sileo.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

import static java.util.stream.Collectors.toList;

@Service
public class SecurityFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = getToken(request);
        var valid = tokenService.validadeJWT(token);

        if (valid != null) {
            Usuario usuario = usuarioRepository.findByEmail(valid)
                    .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

            var authorities = usuario.getRoles().stream()
                    .map(role -> {
                        logger.info("Role encontrada: {}", role.getName());
                        return new SimpleGrantedAuthority(role.getName());
                    })
                    .toList();

            // Logando as roles do usuário
            logger.info("Usuário autenticado: {}, Roles: {}", usuario.getEmail(), authorities);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            logger.info("Autenticação configurada para o usuário: {}", usuario.getEmail());
        }

        filterChain.doFilter(request, response);
    }


    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.replace("Bearer ", "");
    }
}
