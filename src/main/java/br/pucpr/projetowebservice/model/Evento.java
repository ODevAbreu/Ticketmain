package br.pucpr.projetowebservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "TAB_EVENTO")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "NomeEvento")
    private String nomeEvento;

    @Column(name = "DataEvento")
    private Date dataEvento;

    @Column(name = "Lotacao")
    private int lotacao;

    @OneToOne
    @JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
    private Tipo tipo;

    @OneToOne
    @JoinColumn(name = "local_id")
    private Local local;





}
