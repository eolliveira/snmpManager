package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "DISCOATIVO")
@Entity
@Data
public class DiscoAtivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String modelo;
    private String numeroSerie;
    private String capacidade;
    private String usado;
    private String disponivel;

    @ManyToOne
    @JoinColumn(name = "ID_ESTACAO_TRABALHO")
    private EstacaoTrabalhoEntity estacaoTrabalho;

    @OneToMany(mappedBy = "disco")
    private List<DiscoAtivoParticaoEntity> particoes = new ArrayList<>();

}
