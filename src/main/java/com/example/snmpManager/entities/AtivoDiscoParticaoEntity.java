package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.*;

@Table(name = "ATIVODISCOPARTICAO")
@Entity
@Data
public class AtivoDiscoParticaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pontoMontagem;
    private String capacidade;

    @ManyToOne
    @JoinColumn(name = "ID_DISCO_ATIVO")
    private AtivoDiscoEntity disco;

}
