package com.example.snmpManager.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "ATIVO")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public class AtivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Boolean ativo;
    private String numeroSerie;
    private Date dtAquisicao;
    private Date dtExpiracao;
    private Date dtVencimentoGarantia;
    private BigDecimal valorCompra;
    private String fornecedor;
    private String local;
    private String obeservacao;

    @ManyToOne()
    @JoinColumn(name = "id_categoria")
    private CategoriaEntity categoria;
}