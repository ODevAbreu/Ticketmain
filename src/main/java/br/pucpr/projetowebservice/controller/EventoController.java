package br.pucpr.projetowebservice.controller;

import br.pucpr.projetowebservice.dto.EventoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/evento")
@Tag(name = "Evento", description = "APIs de gerenciamento de evento")
public class EventoController {

    private List<EventoDTO> eventos = new ArrayList<>();

    @PostMapping
    @Operation(summary = "Salva um evento", description = "Salva um evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "evento Salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Os dados do evento estão incorretos."),
    })
    public ResponseEntity<EventoDTO> save(@Valid @RequestBody EventoDTO eventoDTO) {
        eventos.add(eventoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoDTO);
    }

    @GetMapping
    @Operation(summary = "Obter a lista de evenos", description = "Retorna a lista de eventos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperado com sucesso"),
    })
    public List<EventoDTO> findAll() {
        return eventos;
    }

    @PutMapping("/{IdEvento}")
    public ResponseEntity<EventoDTO> update(@PathVariable("IdEvento") Integer IdEvento, @RequestBody EventoDTO eventoDTO) {
        for (EventoDTO evento : eventos) {
            if (evento.getIdEvento().equals(IdEvento)) {
                evento.setNomeEvento(eventoDTO.getNomeEvento());
                evento.setDataEvento(eventoDTO.getDataEvento());
                evento.setLotacao(eventoDTO.getLotacao());

                return ResponseEntity.ok(evento);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{IdEvento}")
    public void delete(@PathVariable("IdEvento") Integer IdEvento) {
        eventos.removeIf(evento -> evento.getIdEvento().equals(IdEvento));
    }

}
