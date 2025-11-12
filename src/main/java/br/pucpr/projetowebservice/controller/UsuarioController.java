package br.pucpr.projetowebservice.controller;

import br.pucpr.projetowebservice.dto.UsuarioDTO;
import br.pucpr.projetowebservice.model.Usuario;
import br.pucpr.projetowebservice.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
@Tag(name = "Usuário", description = "APIs de gerenciamento de usuários")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    private List<UsuarioDTO> usuarios = new ArrayList<>();

    @PostMapping
    @Operation(summary = "Salva um usuário", description = "Salva um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário Salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Os dados do usuário estão incorretos."),
    })
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = new ModelMapper().map(usuarioDTO, Usuario.class);
        usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Obter a lista de usuários", description = "Retorna a lista de usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperado com sucesso"),
    })

    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Usuario u : usuarios) {
            usuariosDTO.add(mapper.map(u, UsuarioDTO.class));
        }
        return usuariosDTO;
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable("id") Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = new ModelMapper().map(usuarioDTO, Usuario.class);
        usuarioService.save(usuario);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        boolean removed = usuarios.removeIf(u -> u.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
