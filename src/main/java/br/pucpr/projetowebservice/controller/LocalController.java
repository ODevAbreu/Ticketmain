package br.pucpr.projetowebservice.controller;

import br.pucpr.projetowebservice.dto.LocalDTO;
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
@RequestMapping("/api/v1/local")
@Tag(name = "Local", description = "APIs de gerenciamento de locais de eventos")
public class LocalController {

    private List<LocalDTO> locais = new ArrayList<>();

    @PostMapping
    @Operation(summary = "Salva um local", description = "Cadastra um novo local de evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Local salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Os dados do local estão incorretos."),
    })
    public ResponseEntity<LocalDTO> save(@Valid @RequestBody LocalDTO localDTO) {
        locais.add(localDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(localDTO);
    }

    @GetMapping
    @Operation(summary = "Lista todos os locais", description = "Retorna todos os locais cadastrados")
    public List<LocalDTO> findAll() {
        return locais;
    }

    @PutMapping("/{idLocal}")
    @Operation(summary = "Atualiza um local", description = "Atualiza os dados de um local existente")
    public ResponseEntity<LocalDTO> update(@PathVariable("idLocal") Integer idLocal, @RequestBody LocalDTO localDTO) {
        for (LocalDTO local : locais) {
            if (local.getIdLocal().equals(idLocal)) {
                local.setNomeLocal(localDTO.getNomeLocal());
                local.setEndereco(localDTO.getEndereco());
                local.setCidade(localDTO.getCidade());
                local.setEstado(localDTO.getEstado());
                local.setCapacidade(localDTO.getCapacidade());
                return ResponseEntity.ok(local);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idLocal}")
    @Operation(summary = "Remove um local", description = "Deleta um local pelo ID")
    public void delete(@PathVariable("idLocal") Integer idLocal) {
        locais.removeIf(local -> local.getIdLocal().equals(idLocal));
    }
}
