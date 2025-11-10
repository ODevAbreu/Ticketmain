package br.pucpr.projetowebservice.service;

import br.pucpr.projetowebservice.dto.TipoDTO;
import br.pucpr.projetowebservice.model.Tipo;
import br.pucpr.projetowebservice.repository.TipoRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TipoService {

    private final TipoRepository tipoRepository;

    public Tipo save(Tipo tipo) {return tipoRepository.save(tipo);}

}