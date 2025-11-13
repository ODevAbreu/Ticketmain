package br.pucpr.projetowebservice.controller;

import br.pucpr.projetowebservice.dto.LocalDTO;
import br.pucpr.projetowebservice.model.Local;
import br.pucpr.projetowebservice.service.LocalService;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/local")
@Tag(name = "Local", description = "APIs de gerenciamento de locais de eventos")
@AllArgsConstructor
public class LocalController {

    private final LocalService localService;
    private final ModelMapper mapper = new ModelMapper();

    @PostMapping
    @Operation(summary = "Salva um local", description = "Cadastra um novo local de evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Local salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Os dados do local estão incorretos."),
    })
    public ResponseEntity<LocalDTO> save(@Valid @RequestBody LocalDTO localDTO) {
        // VALIDAÇÃO: Force o ID para null para garantir INSERT
        localDTO.setIdLocal(null);

        // MAPEAMENTO MANUAL - corrigindo os nomes diferentes
        Local local = new Local();
        local.setLocal(localDTO.getLocal());  // DTO: nomeLocal → Model: local
        local.setEndereco(localDTO.getEndereco());

        Local salvo = localService.save(local);

        // Converte de volta para DTO
        LocalDTO response = new LocalDTO();
        response.setIdLocal(salvo.getIdLocal());
        response.setLocal(salvo.getLocal());  // Model: local → DTO: nomeLocal
        response.setEndereco(salvo.getEndereco());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Lista todos os locais", description = "Retorna todos os locais cadastrados")
    public List<LocalDTO> findAll() {
        return localService.findAll()
                .stream()
                .map(local -> mapper.map(local, LocalDTO.class))
                .toList();
    }

    @PutMapping("/{idLocal}")
    @Operation(summary = "Atualiza um local", description = "Atualiza os dados de um local existente")
    public ResponseEntity<LocalDTO> update(@PathVariable("idLocal") Integer idLocal, @RequestBody LocalDTO localDTO) {
        Local existente = localService.findById(idLocal);
        existente.setLocal(localDTO.getLocal());
        existente.setEndereco(localDTO.getEndereco());
        Local atualizado = localService.save(existente);
        return ResponseEntity.ok(mapper.map(atualizado, LocalDTO.class));
    }

    @DeleteMapping("/{idLocal}")
    @Operation(summary = "Remove um local", description = "Deleta um local pelo ID")
    public ResponseEntity<Void> delete(@PathVariable("idLocal") Integer idLocal) {
        localService.delete(idLocal);
        return ResponseEntity.noContent().build();
    }
}
