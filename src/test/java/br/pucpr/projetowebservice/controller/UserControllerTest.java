package br.pucpr.projetowebservice.controller;

import br.pucpr.projetowebservice.model.Usuario;
import br.pucpr.projetowebservice.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService userService;

    @Test
    public void testGetEndpoint() throws Exception {
        Usuario user = new Usuario();
        user.setEmail("email@email.com");
        List<Usuario> users = new ArrayList<>();
        users.add(user);
        // Given
        when(userService.findAll()).thenReturn(users);

        // When/Then
        mockMvc.perform(get("/api/v1/usuario")
                        .with(user("admin").roles("ADMIN","USER"))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].email").
                        value("email@email.com"));

    }

}
