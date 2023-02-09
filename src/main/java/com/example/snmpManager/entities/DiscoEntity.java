package com.example.snmpManager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String usado;
    private String disponivel;

    @ManyToOne
    @JoinColumn(name = "ID_ESTACAO_TRABALHO")
    private EstacaoTrabalhoEntity estacaoTrabalho;

    @OneToMany(mappedBy = "disco", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiscoParticaoEntity> particoes = new ArrayList<>();

    public DiscoEntity() {
    }
}
