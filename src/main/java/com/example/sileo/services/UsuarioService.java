package com.example.sileo.services;

import com.example.sileo.domain.Usuario.Usuario;
import com.example.sileo.domain.Usuario.UsuarioRegisterDTO;
import com.example.sileo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Transactional
    public Usuario register(UsuarioRegisterDTO usuarioRegisterDTO) {
        Usuario usuario = new Usuario();
        usuario.setCpf(usuarioRegisterDTO.getCpf());
        usuario.setNome(usuarioRegisterDTO.getNome());
        usuario.setEmail(usuarioRegisterDTO.getEmail());

        usuario.setSenha(passwordEncoder.encode(usuarioRegisterDTO.getSenha()));

        return usuarioRepository.save(usuario);
    }


}
