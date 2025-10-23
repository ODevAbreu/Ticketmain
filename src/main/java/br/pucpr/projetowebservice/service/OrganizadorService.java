package br.pucpr.projetowebservice.service;

import br.pucpr.projetowebservice.model.Organizador;
import br.pucpr.projetowebservice.model.Usuario;
import br.pucpr.projetowebservice.repository.OrganizadorRepository;
import br.pucpr.projetowebservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizadorService {

    private final OrganizadorRepository organizadorRepository;

    public Organizador save(Organizador user) {
        return organizadorRepository.save(user);
    }

}