package br.pucpr.projetowebservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "TAB_TIPO")
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tipo")
    private long id_tipo;

    @Column(name = "Tipo")
    private String NomeEvento;

    @OneToOne(mappedBy = "tipo")
    private Evento evento;

}