package br.pucpr.projetowebservice.security;

import br.pucpr.projetowebservice.model.Usuario;
import br.pucpr.projetowebservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Agora vem do ApplicationConfig

    public AuthResponse authenticate(AuthRequest request) {
        // Busca o usuário no banco
        Optional<Usuario> user = userRepository.findByEmail(request.getEmail());
        if (user.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        // Verifica a senha MANUALMENTE
        if (!passwordEncoder.matches(request.getPassword(), user.get().getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        // Gera o token JWT
        UserAuthentication userAuth = new UserAuthentication();
        userAuth.setEmail(user.get().getEmail());
        userAuth.setRole(user.get().getRole());

        String jwtToken = jwtService.generateToken(userAuth);

        // Monta a resposta
        AuthResponse response = new AuthResponse();
        response.setToken(jwtToken);
        response.setEmail(user.get().getEmail());
        response.setExpires(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));

        return response;
    }
}