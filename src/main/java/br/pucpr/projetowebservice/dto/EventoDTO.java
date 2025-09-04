package br.pucpr.projetowebservice.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EventoDTO {
    private Integer IdEvento;
    private String NomeEvento;
    private Date DataEvento;
    private int Lotacao;

}
