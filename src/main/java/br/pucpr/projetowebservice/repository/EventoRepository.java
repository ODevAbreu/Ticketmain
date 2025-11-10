package br.pucpr.projetowebservice.repository;

import br.pucpr.projetowebservice.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    Evento findById(Long Id);
}
