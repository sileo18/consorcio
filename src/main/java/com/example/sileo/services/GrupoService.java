package com.example.sileo.services;

import com.example.sileo.domain.Grupo.Grupo;
import com.example.sileo.domain.Grupo.GrupoCreateDTO;
import com.example.sileo.repositories.GrupoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Transactional
    public Grupo create(GrupoCreateDTO grupoCreateDTO) {
        Grupo grupo = new Grupo();
        grupo.setCodigo(grupoCreateDTO.codigo);
        grupo.setadmnistracaoPorcentagem(grupoCreateDTO.admnistracaoPorcentagem);
        grupo.setDuracaoMeses(grupoCreateDTO.duracaoMeses);
        return grupoRepository.save(grupo);

    }

    @Transactional
    public List<Grupo> getAll() {
        return grupoRepository.findAll();
    }

}
