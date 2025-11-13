package br.pucpr.projetowebservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngressoDTO {
    private Long id;
    private Double preco;
    private String tipoIngresso;
    private EventoDTO evento;
    private UsuarioDTO usuario;
}
