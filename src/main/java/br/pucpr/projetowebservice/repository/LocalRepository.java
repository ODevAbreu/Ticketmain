package br.pucpr.projetowebservice.repository;

import br.pucpr.projetowebservice.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {
}
