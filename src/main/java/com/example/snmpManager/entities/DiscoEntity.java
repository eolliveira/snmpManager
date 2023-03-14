package com.example.snmpManager.entities;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "DISCO")
@Entity
@Getter
@Setter
public class DiscoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DISCO")
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

    public DiscoEntity(DiscoDTO discoDto, EstacaoTrabalhoEntity estacaoTrabalho) {
        this.nome = discoDto.getNome();
        this.modelo = discoDto.getModelo();
        this.numeroSerie = discoDto.getNumeroSerie();
        this.capacidade = discoDto.getCapacidade();
        this.estacaoTrabalho = estacaoTrabalho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscoEntity)) return false;
        DiscoEntity that = (DiscoEntity) o;
        return Objects.equals(numeroSerie, that.numeroSerie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroSerie);
    }
}
