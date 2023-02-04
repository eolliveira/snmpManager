package com.example.snmpManager.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "DISCOATIVO")
@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "disco", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiscoAtivoParticaoEntity> particoes = new ArrayList<>();

    public DiscoAtivoEntity() {
    }
}
