package br.pucpr.projetowebservice.service;

import br.pucpr.projetowebservice.model.Tipo;
import br.pucpr.projetowebservice.repository.TipoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoService {

    private final TipoRepository tipoRepository;

    public Tipo save(Tipo tipo) {return tipoRepository.save(tipo);}

    public List<Tipo> findAll() {
        return tipoRepository.findAll();
    }
}