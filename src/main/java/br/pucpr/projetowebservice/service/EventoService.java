package br.pucpr.projetowebservice.service;

import br.pucpr.projetowebservice.model.Evento;
import br.pucpr.projetowebservice.model.Tipo;
import br.pucpr.projetowebservice.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }
    public void delete(Integer idEvento) {
        eventoRepository.deleteById(idEvento);
    }
}