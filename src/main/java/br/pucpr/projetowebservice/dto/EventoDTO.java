package br.pucpr.projetowebservice.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {
    private Integer idEvento;
    private String nomeEvento;
    private Date dataEvento;
    private int lotacao;
    private TipoDTO tipo;

//como inserir os dados com o join do tipo
//    {
//        "nomeEvento": "BGS",
//            "dataEvento": "2025-11-12T13:04:55.349Z",
//            "lotacao": 10,
//            "tipo": {
//        "idTipo": 1
//    }
//    }
}
