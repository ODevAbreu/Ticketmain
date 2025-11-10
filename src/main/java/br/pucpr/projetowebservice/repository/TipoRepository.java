package br.pucpr.projetowebservice.repository;

import br.pucpr.projetowebservice.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    Tipo findById(Long Id);
}
