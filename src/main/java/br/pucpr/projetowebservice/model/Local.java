package br.pucpr.projetowebservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "TAB_LOCAL")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_local")
    private Integer idLocal;

    @Column(name = "local")
    private String local;

    @OneToOne(mappedBy = "local")
    private Evento evento;

}