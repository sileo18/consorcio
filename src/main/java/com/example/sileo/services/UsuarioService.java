package com.example.sileo.services;

import com.example.sileo.domain.Usuario.*;
import com.example.sileo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.web.servlet.function.ServerResponse.notFound;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Transactional
    public Usuario register(UsuarioRegisterDTO usuarioRegisterDTO) {
        Usuario usuario = usuarioMapper.toUsuario(usuarioRegisterDTO);
        usuario.setSenha(passwordEncoder.encode(usuarioRegisterDTO.getSenha()));

        return usuarioRepository.save(usuario);
    }

    //Get All
    @Transactional
    public List<UsuarioGetAllDTO> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toUsuarioDTOs(usuarios);
    }

    //Update
    @Transactional
    public Usuario update(UUID id, UsuarioUpdateDTO usuarioUpdateDTO) {
        if(usuarioRepository.existsById(id)) {
            Usuario usuario = usuarioMapper.toUsuario(usuarioUpdateDTO);
            usuario.setId(id);
            usuario.setSenha(passwordEncoder.encode(usuarioUpdateDTO.getSenha()));
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }
}
