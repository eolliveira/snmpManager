package com.example.snmpManager.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public abstract class AtivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATIVO")
    private Long id;
    private String nome;
    private String fabricante;
    private String modelo;
    private String descricao;
    private String numeroSerie;
    private Boolean inativo;
    @Enumerated(EnumType.STRING)
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
    private List<MovimentoEntity> movimentos = new ArrayList<>();

    @OneToMany(mappedBy = "ativo")
    private List<LicencaEntity> licencas = new ArrayList<>();


}
