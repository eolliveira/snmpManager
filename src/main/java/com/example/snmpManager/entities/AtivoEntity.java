package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "ATIVO")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TP_ATIVO")
@Data
public abstract class AtivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String fabricante;
    private String modelo;
    private String descricao;
    private String numeroSerie;
    private Boolean inativo;
    private StatusAtivo status;
    private Date dtAquisicao;
    private Date dtVencimentoGarantia;
    private Date dtExpiracao;
    private Date ultimoSincronismo;
    private BigDecimal valorCompra;
    private String fornecedor;
    private String obeservacao;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "ativo")
    private List<MovimentoAtivoEntity> movimentos = new ArrayList<>();

}
