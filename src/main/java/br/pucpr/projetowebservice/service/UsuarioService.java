package br.pucpr.projetowebservice.service;

import br.pucpr.projetowebservice.model.Usuario;
import br.pucpr.projetowebservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // ← ADICIONE ESTE

    public Usuario save(Usuario user) {
        // CORREÇÃO: Codificar a senha antes de salvar
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userRepository.save(user);
    }

    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    public Optional<Usuario> findByEmail(String mail) {
        return userRepository.findByEmail(mail);
    }

    public Usuario update(Usuario usuario) {
        Usuario existente = userRepository.findById(Math.toIntExact(usuario.getId()))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        existente.setNome(usuario.getNome());
        existente.setEmail(usuario.getEmail());

        // CORREÇÃO: Codificar a senha também no update
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            existente.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        existente.setDataNascimento(usuario.getDataNascimento());
        return userRepository.save(existente);
    }
}