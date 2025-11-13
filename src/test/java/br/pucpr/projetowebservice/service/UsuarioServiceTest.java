package br.pucpr.projetowebservice.service;

import br.pucpr.projetowebservice.model.Usuario;
import br.pucpr.projetowebservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UsuarioService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Given
        Usuario user = new Usuario();
        user.setEmail("email@email.com");
        List<Usuario> users = new ArrayList<>();
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        // When
        List<Usuario> result = userService.findAll();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmail()).isEqualTo("email@email.com");
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindByEmail() {
        // Given
        Usuario user = new Usuario();
        user.setEmail("email@email.com");

        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));

        // When
        Optional<Usuario> result = userService.findByEmail("email@email.com");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.get().getEmail()).isEqualTo("email@email.com");
        verify(userRepository, times(1)).findByEmail("email@email.com");
    }
}
