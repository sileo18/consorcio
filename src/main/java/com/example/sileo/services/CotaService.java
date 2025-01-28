package com.example.sileo.services;

import com.example.sileo.domain.Cota.Cota;
import com.example.sileo.domain.Cota.CotaCreateDTO;
import com.example.sileo.repositories.CotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CotaService {

    @Autowired
    private CotaRepository cotaRepository;

    public Cota create(CotaCreateDTO cotaCreateDTO) {

        Cota cota = new Cota();

        cota.setCodigo(cotaCreateDTO.getCodigo());
        cota.setCredito(cotaCreateDTO.getCredito());
        cota.setPlanoMeses(cotaCreateDTO.getPlanoMeses());
        cota.setTotalPago(cotaCreateDTO.getTotalPago());


        return cotaRepository.save(cota);
    }

}
