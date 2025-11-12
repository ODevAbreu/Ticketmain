package br.pucpr.projetowebservice.service;

import br.pucpr.projetowebservice.model.Usuario;
import br.pucpr.projetowebservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UserRepository userRepository;

    public Usuario save(Usuario user) {
        return userRepository.save(user);
    }

    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    public Usuario findByEmail(String mail) {
        return userRepository.findByEmail(mail);
    }
}