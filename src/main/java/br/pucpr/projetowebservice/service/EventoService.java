package br.pucpr.projetowebservice.service;

import br.pucpr.projetowebservice.model.Evento;
import br.pucpr.projetowebservice.model.Tipo;
import br.pucpr.projetowebservice.repository.EventoRepository;
import br.pucpr.projetowebservice.repository.TipoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final TipoRepository tipoRepository;

    @Transactional
    public Evento save(Evento evento) {
        // Verifica se o Tipo existe antes de salvar o evento
        if (evento.getTipo() != null && evento.getTipo().getIdTipo() != 0) {
            Optional<Tipo> tipoOpt = tipoRepository.findById(evento.getTipo().getIdTipo());
            tipoOpt.ifPresent(evento::setTipo);
        } else {
            evento.setTipo(null);
        }

        return eventoRepository.save(evento);
    }

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }
    public void delete(Integer idEvento) {
        eventoRepository.deleteById(idEvento);
    }
}