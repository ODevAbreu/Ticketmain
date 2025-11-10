package br.pucpr.projetowebservice.service;

import br.pucpr.projetowebservice.model.Evento;
import br.pucpr.projetowebservice.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

}