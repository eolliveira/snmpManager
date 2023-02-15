package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.*;

@Table(name = "ATIVODISCOPARTICAO")
@Entity
@Data
public class DiscoParticaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATIVODISCOPARTICAO")
    private Long id;
    private String pontoMontagem;
    private String capacidade;
    private String usado;

    @ManyToOne
    @JoinColumn(name = "ID_ATIVODISCO")
    private DiscoEntity disco;

}
