package br.pucpr.projetowebservice.controller;

import br.pucpr.projetowebservice.dto.OrganizadorDTO;
import br.pucpr.projetowebservice.dto.UsuarioDTO;
import br.pucpr.projetowebservice.model.Organizador;
import br.pucpr.projetowebservice.model.Usuario;
import br.pucpr.projetowebservice.service.OrganizadorService;
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
@RequestMapping("/api/v1/organizador")
@Tag(name = "Organizador", description = "APIs de gerenciamento de organizadores")
@AllArgsConstructor
public class OrganizadorController {

    private final OrganizadorService organizadorService;

    @PostMapping
    @Operation(summary = "Salva um organizador", description = "Salva um organizador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Organizador Salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Os dados do organizador estão incorretos."),
    })
    public ResponseEntity<OrganizadorDTO> save(@Valid @RequestBody OrganizadorDTO organizadorDTO) {
        Organizador organizador = new ModelMapper().map(organizadorDTO, Organizador.class);
        organizadorService.save(organizador);
        organizadorDTO.setId(organizador.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(organizadorDTO);
    }

    @GetMapping
    @Operation(summary = "Obter a lista de organizadores", description = "Retorna a lista de organizadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperado com sucesso"),
    })
    public ResponseEntity<List<OrganizadorDTO>> findAll() {
        List<Organizador> organizadores = organizadorService.findAll();
        List<OrganizadorDTO> organizadorDTOS = organizadores.stream().map(user -> new ModelMapper().map(user, OrganizadorDTO.class)).
                toList();
        return new ResponseEntity<>(organizadorDTOS, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> update(@PathVariable("id") Integer id, @RequestBody OrganizadorDTO organizadorDTO) {
        Organizador organizador = new ModelMapper().map(organizadorDTO, Organizador.class);
        organizadorService.save(organizador);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        //boolean removed = organizadores.removeIf(u -> u.getId().equals(id));
        //return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        organizadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
