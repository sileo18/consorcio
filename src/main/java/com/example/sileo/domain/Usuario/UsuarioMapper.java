package com.example.sileo.domain.Usuario;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {


    UsuarioGetAllDTO toUsuarioDTO(Usuario usuario);

    List<UsuarioGetAllDTO> toUsuarioDTOs(List<Usuario> usuarios);


    Usuario toUsuario(UsuarioGetAllDTO usuarioDTO);

    List<Usuario> toUsuarios(List<UsuarioGetAllDTO> usuarioDTOs);


    Usuario toUsuario(UsuarioRegisterDTO usuarioRegisterDTO);

    Usuario toUsuario(UsuarioUpdateDTO usuarioUpdateDTO);
}