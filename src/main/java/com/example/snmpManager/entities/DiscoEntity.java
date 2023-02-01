package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.*;

@Table(name = "DISCOATIVO")
@Entity
@Data
public class DiscoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String modelo;
    private String numeroSerie;
    private String capacidade;
    private String usado;
    private String disponivel;
}
