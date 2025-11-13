package br.pucpr.projetowebservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LocalDTO {

    private Integer idLocal;

    @NotBlank
    private String nomeLocal;

    @NotBlank
    private String endereco;

}
