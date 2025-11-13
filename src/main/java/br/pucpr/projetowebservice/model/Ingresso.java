package br.pucpr.projetowebservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TAB_INGRESSO")
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingresso")
    private Long id;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private String tipoIngresso; // Ex: VIP, Pista etc.


    @ManyToOne
    @JoinColumn(name = "id_evento", referencedColumnName = "id")
    private Evento evento;


    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "ID")
    private Usuario usuario;
}
