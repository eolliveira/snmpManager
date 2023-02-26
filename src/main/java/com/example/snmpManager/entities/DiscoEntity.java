package com.example.snmpManager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "ATIVODISCO")
@Entity
@Getter
@Setter
public class DiscoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATIVODISCO")
    private Long id;
    private String nome;
    private String modelo;
    private String numeroSerie;
    private String capacidade;

    @ManyToOne
    @JoinColumn(name = "ID_ESTACAO_TRABALHO")
    private EstacaoTrabalhoEntity estacaoTrabalho;

    @OneToMany(mappedBy = "disco", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DiscoParticaoEntity> particoes = new HashSet<>();

    public DiscoEntity() {
    }
}
