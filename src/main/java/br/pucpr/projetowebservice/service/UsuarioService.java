package br.pucpr.projetowebservice.service;

import br.pucpr.projetowebservice.model.Usuario;
import br.pucpr.projetowebservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UserRepository userRepository;

    public Usuario save(Usuario user) {
        return userRepository.save(user);
    }

}