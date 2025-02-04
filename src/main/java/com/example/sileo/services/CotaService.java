package com.example.sileo.services;

import com.example.sileo.domain.Cota.Cota;
import com.example.sileo.domain.Cota.CotaCreateDTO;
import com.example.sileo.domain.Cota.CotaGetDTO;
import com.example.sileo.domain.Grupo.Grupo;
import com.example.sileo.domain.Grupo.GrupoGetDTO;
import com.example.sileo.domain.Usuario.Usuario;
import com.example.sileo.domain.Usuario.UsuarioGetDTO;
import com.example.sileo.repositories.CotaRepository;
import com.example.sileo.repositories.GrupoRepository;
import com.example.sileo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CotaService {

    @Autowired
    private CotaRepository cotaRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Cota create(CotaCreateDTO cotaCreateDTO) {

        Cota cota = new Cota();

        Grupo grupo = grupoRepository.findById(cotaCreateDTO.getGrupoId())
                .orElseThrow(() -> new IllegalArgumentException("Grupo not found"));

        Usuario usuario = usuarioRepository.findById(cotaCreateDTO.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario not found"));

        cota.setGrupo(grupo);
        cota.setUsuario(usuario);
        cota.setCodigo(cotaCreateDTO.getCodigo());
        cota.setCredito(cotaCreateDTO.getCredito());
        cota.setPlanoMeses();
        cota.setCategoria(cotaCreateDTO.getCredito());
        cota.setTotalPago();
        cota.setParcela();

        return cotaRepository.save(cota);
    }

    public CotaGetDTO getCota(UUID cotaId) {
        Cota cota = cotaRepository.findById(cotaId)
                .orElseThrow(() -> new IllegalArgumentException("Cota not found"));

        GrupoGetDTO grupoDTO = new GrupoGetDTO(
                cota.getGrupo().getCodigo(),
                cota.getGrupo().getAdmnistracaoPorcentagem(),
                cota.getGrupo().getDuracaoMeses()
        );

        UsuarioGetDTO usuarioDTO = new UsuarioGetDTO(
                cota.getUsuario().getNome()
        );

        return new CotaGetDTO(
                cota.getCodigo(),
                cota.getCredito(),
                cota.getPlanoMeses(),
                cota.getTotalPago(),
                cota.getParcela(),
                cota.getCategoria(),
                grupoDTO,
                usuarioDTO
        );
    }
}