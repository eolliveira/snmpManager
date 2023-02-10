package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "ATIVOMOVIMENTO")
@Entity
@Data
public class MovimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATIVOMOVIMENTO")
    private Long id;
    private String descricao;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dtMovimento;
    private StatusAtivo statusAtivoAnterior;
    private StatusAtivo statusAtivo;

    @ManyToOne
    @JoinColumn(name = "ID_ATIVO")
    private AtivoEntity ativo;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "movimento")
    private List<AnexoEntity> anexos = new ArrayList<>();
}
