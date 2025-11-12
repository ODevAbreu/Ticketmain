package br.pucpr.projetowebservice.controller;

import br.pucpr.projetowebservice.dto.TipoDTO;
import br.pucpr.projetowebservice.dto.UsuarioDTO;
import br.pucpr.projetowebservice.exception.BusinessException;
import br.pucpr.projetowebservice.model.Tipo;
import br.pucpr.projetowebservice.model.Usuario;
import br.pucpr.projetowebservice.service.TipoService;
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
@RequestMapping("/api/v1/tipo")
@Tag(name = "Tipo", description = "APIs de gerenciamento de tipo de evento")
@AllArgsConstructor
public class TipoController {

    private final TipoService tipoService;

    private List<TipoDTO> tipos = new ArrayList<>();

    @PostMapping
    @Operation(summary = "Salva um tipo", description = "Salva um tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "tipo Salvo com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Os dados do tipo estão incorretos."),
    })
    public ResponseEntity<TipoDTO> save(@Valid @RequestBody TipoDTO tipoDTO) {
        Tipo tipo = new ModelMapper().map(tipoDTO, Tipo.class);
        tipoService.save(tipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoDTO);
    }

    @GetMapping
    @Operation(summary = "Obter a lista de tipos", description = "Retorna a lista de tipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperado com sucesso"),
    })
    public List<TipoDTO> findAll() {
        List<Tipo> tipo = tipoService.findAll();
        List<TipoDTO> tipoDTO = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Tipo u : tipo) {
            tipoDTO.add(mapper.map(u, TipoDTO.class));
        }
        return tipoDTO;
    }

    @PutMapping("/{idTipo}")
    public ResponseEntity<TipoDTO> update(@PathVariable("idTipo") Integer idTipo, @RequestBody TipoDTO tipoDTO) throws BusinessException {
//            if (idTipo == null || tipoDTO.getIdTipo() == null) {
//                throw new BusinessException("ID_REQUIRED","O ID é necessário");
//            } Comentei pois estava validando apenas o id passado pelo json e nao pela URL
            tipoDTO.setIdTipo(idTipo);
            Tipo tipo = new ModelMapper().map(tipoDTO, Tipo.class);
            tipoService.save(tipo);

            return ResponseEntity.status(HttpStatus.CREATED).body(tipoDTO);
    }

    @DeleteMapping("/{idTipo}")
    public void delete(@PathVariable("idTipo") Integer idTipo) {
        tipoService.delete(idTipo);
    }

}
