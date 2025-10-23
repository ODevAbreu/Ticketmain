package br.pucpr.projetowebservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "TAB_ORGANIZADOR")
public class Organizador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CNPJ")
    private Long cnpj;

    @Column(name = "NOME FANTASIA")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

}