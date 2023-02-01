package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "MOVIMENTOATIVO")
@Entity
@Data
public class MovimentoAtivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Date dtMovimento;
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
