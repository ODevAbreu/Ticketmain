package br.pucpr.projetowebservice.security;

import br.pucpr.projetowebservice.model.Organizador;
import br.pucpr.projetowebservice.model.Usuario;
import br.pucpr.projetowebservice.repository.OrganizadorRepository;
import br.pucpr.projetowebservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class AuthService {

    //private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final OrganizadorRepository organizadorRepository;

    public AuthResponse authenticate(AuthRequest request) {
        if (request.getTipologin().equals("usuario")) {
            Usuario user = userRepository.findByEmail(request.getEmail());
            if (user == null || !user.getSenha().equals(request.getPassword())) {
                throw new RuntimeException("Usuario incorreto");
            }

            UserAuthentication userAuthentication = new UserAuthentication();
            userAuthentication.setEmail(request.getEmail());

            var jwtToken = jwtService.generateToken(userAuthentication);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(jwtToken);
            authResponse.setEmail(user.getEmail());
            authResponse.setExpires(new Date(System.currentTimeMillis() + 1000 * 60 * 24));

            return authResponse;
        }

        if (request.getTipologin().equals("organizador")) {
            Organizador organizador = organizadorRepository.findByEmail(request.getEmail());

            if (organizador == null || !organizador.getSenha().equals(request.getPassword())) {
                throw new RuntimeException(" incorreto");
            }
            UserAuthentication userAuthentication = new UserAuthentication();
            userAuthentication.setEmail(request.getEmail());
            userAuthentication.setRole(Role.USER);

            var jwtToken = jwtService.generateToken(userAuthentication);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(jwtToken);
            authResponse.setEmail(organizador.getEmail());
            authResponse.setExpires(new Date(System.currentTimeMillis() + 1000 * 60 * 24));

            return authResponse;
        }

        return null;
    }


}
