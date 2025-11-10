package br.pucpr.projetowebservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TipoDTO {
    private Integer IdTipo;
    private String Tipo;

}

