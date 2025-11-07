package br.pucpr.projetowebservice.repository;

import br.pucpr.projetowebservice.model.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador, Integer> {
    Organizador findByEmail(String email);

}
