package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.*;

@Table(name = "DISCOATIVOPARTICAO")
@Entity
@Data
public class DiscoAtivoParticaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pontoMontagem;
    private String capacidade;

    @ManyToOne
    @JoinColumn(name = "ID_DISCO_ATIVO")
    private DiscoAtivoEntity disco;

}
