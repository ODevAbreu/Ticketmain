package br.pucpr.projetowebservice.controller;

import br.pucpr.projetowebservice.dto.IngressoDTO;
import br.pucpr.projetowebservice.model.Ingresso;
import br.pucpr.projetowebservice.service.IngressoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper mapper = new ModelMapper();

    @PostMapping
    @Operation(summary = "Salvar ingresso")
    public ResponseEntity<IngressoDTO> save(@RequestBody IngressoDTO dto) {
        Ingresso ingresso = mapper.map(dto, Ingresso.class);
        ingressoService.save(ingresso);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    @Operation(summary = "Listar ingressos")
    public List<IngressoDTO> findAll() {
        return ingressoService.findAll()
                .stream()
                .map(i -> mapper.map(i, IngressoDTO.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar ingresso por ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingressoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
