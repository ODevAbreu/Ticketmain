package br.pucpr.projetowebservice.controller;

import br.pucpr.projetowebservice.dto.IngressoDTO;
import br.pucpr.projetowebservice.model.Evento;
import br.pucpr.projetowebservice.model.Ingresso;
import br.pucpr.projetowebservice.model.Usuario;
import br.pucpr.projetowebservice.service.EventoService;
import br.pucpr.projetowebservice.service.IngressoService;
import br.pucpr.projetowebservice.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ingresso")
@Tag(name = "Ingresso", description = "APIs de gerenciamento de ingressos")
@AllArgsConstructor
public class IngressoController {

    private final IngressoService ingressoService;
    private final EventoService eventoService;
    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar ingresso")
    public ResponseEntity<IngressoDTO> save(@RequestBody IngressoDTO dto) {
        Ingresso ingresso = new Ingresso();
        ingresso.setPreco(dto.getPreco());
        ingresso.setTipoIngresso(dto.getTipoIngresso());

        // Busca e define o Evento (usando long)
        if (dto.getEventoId() != null) {
            Evento evento = findEventoById(dto.getEventoId());
            ingresso.setEvento(evento);
        }

        // Busca e define o Usuario (usando Long)
        if (dto.getUsuarioId() != null) {
            Usuario usuario = findUsuarioById(dto.getUsuarioId());
            ingresso.setUsuario(usuario);
        }

        Ingresso saved = ingressoService.save(ingresso);
        IngressoDTO response = toDTO(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar ingressos")
    public List<IngressoDTO> findAll() {
        return ingressoService.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ingresso por ID")
    public ResponseEntity<IngressoDTO> update(
            @PathVariable Long id,
            @RequestBody IngressoDTO dto) {

        Ingresso ingressoExistente = ingressoService.findById(id);

        // Atualiza os campos básicos
        ingressoExistente.setPreco(dto.getPreco());
        ingressoExistente.setTipoIngresso(dto.getTipoIngresso());

        // Atualiza relacionamentos se fornecidos
        if (dto.getEventoId() != null) {
            Evento evento = findEventoById(dto.getEventoId());
            ingressoExistente.setEvento(evento);
        }

        if (dto.getUsuarioId() != null) {
            Usuario usuario = findUsuarioById(dto.getUsuarioId());
            ingressoExistente.setUsuario(usuario);
        }

        Ingresso updated = ingressoService.save(ingressoExistente);
        IngressoDTO response = toDTO(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar ingresso por ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingressoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos auxiliares para buscar entidades pelos IDs
    private Evento findEventoById(Long id) {
        return eventoService.findAll().stream()
                .filter(e -> e.getId() == id) // Evento.id é long (primitive)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Evento não encontrado com ID: " + id));
    }

    private Usuario findUsuarioById(Long id) {
        return usuarioService.findAll().stream()
                .filter(u -> u.getId().equals(id)) // Usuario.id é Long (object)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    // Método auxiliar para converter Entity para DTO
    private IngressoDTO toDTO(Ingresso ingresso) {
        IngressoDTO dto = new IngressoDTO();
        dto.setId(ingresso.getId());
        dto.setPreco(ingresso.getPreco());
        dto.setTipoIngresso(ingresso.getTipoIngresso());

        if (ingresso.getEvento() != null) {
            dto.setEventoId(ingresso.getEvento().getId()); // Evento.id é long
        }

        if (ingresso.getUsuario() != null) {
            dto.setUsuarioId(ingresso.getUsuario().getId()); // Usuario.id é Long
        }

        return dto;
    }
}