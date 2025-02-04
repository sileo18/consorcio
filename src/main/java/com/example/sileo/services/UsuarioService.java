package com.example.sileo.services;

import com.example.sileo.domain.Usuario.*;
import com.example.sileo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario register(UsuarioRegisterDTO usuarioRegisterDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRegisterDTO.getNome());
        usuario.setEmail(usuarioRegisterDTO.getEmail());
        usuario.setCpf(usuarioRegisterDTO.getCpf());
        usuario.setSenha(passwordEncoder.encode(usuarioRegisterDTO.getSenha()));

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public List<UsuarioGetAllDTO> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> {
            UsuarioGetAllDTO dto = new UsuarioGetAllDTO();
            dto.setId(usuario.getId());
            dto.setNome(usuario.getNome());
            dto.setEmail(usuario.getEmail());
            dto.setCpf(usuario.getCpf());
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public Usuario update(UUID id, UsuarioUpdateDTO usuarioUpdateDTO) {
        if (usuarioRepository.existsById(id)) {
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome(usuarioUpdateDTO.getNome());
            usuario.setEmail(usuarioUpdateDTO.getEmail());
            usuario.setCpf(usuarioUpdateDTO.getCpf());
            usuario.setSenha(passwordEncoder.encode(usuarioUpdateDTO.getSenha()));
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }
}