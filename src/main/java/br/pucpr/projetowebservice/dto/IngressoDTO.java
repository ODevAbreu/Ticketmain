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

    // Agora ambos são Long para compatibilidade
    private Long eventoId;    // Compatível com Evento.id (long)
    private Long usuarioId;   // Compatível com Usuario.id (Long)
}