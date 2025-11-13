package br.pucpr.projetowebservice.controller;

import br.pucpr.projetowebservice.dto.EventoDTO;
import br.pucpr.projetowebservice.model.Evento;
import br.pucpr.projetowebservice.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/evento")
@Tag(name = "Evento", description = "APIs de gerenciamento de evento")
@AllArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    private List<EventoDTO> eventos = new ArrayList<>();

    @PostMapping
    @Operation(summary = "Salva um evento", description = "Salva um evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "evento Salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Os dados do evento estão incorretos."),
    })
    public ResponseEntity<EventoDTO> save(@Valid @RequestBody EventoDTO eventoDTO) {
        eventoDTO.setIdEvento(null);

        ModelMapper mapper = new ModelMapper();
        Evento evento = mapper.map(eventoDTO, Evento.class);

        Evento saved = eventoService.save(evento);

        // Retorna o objeto salvo (com ID gerado)
        EventoDTO response = mapper.map(saved, EventoDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Obter a lista de eventos", description = "Retorna a lista de eventos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperado com sucesso"),
    })
    public List<EventoDTO> findAll() {
        List<Evento> evento = eventoService.findAll();
        List<EventoDTO> eventoDTO = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Evento u : evento) {
            eventoDTO.add(mapper.map(u, EventoDTO.class));
        }
        return eventoDTO;
    }

    @PutMapping("/{idEvento}")
    public ResponseEntity<EventoDTO> update(@PathVariable("idEvento") Integer idEvento, @RequestBody EventoDTO eventoDTO) {
        eventoDTO.setIdEvento(idEvento);
        Evento evento = new ModelMapper().map(eventoDTO, Evento.class);
        eventoService.save(evento);

        return ResponseEntity.status(HttpStatus.CREATED).body(eventoDTO);
    }

    @DeleteMapping("/{idEvento}")
    public void delete(@PathVariable("idEvento") Integer idEvento) {
        eventoService.delete(idEvento);
    }

}
