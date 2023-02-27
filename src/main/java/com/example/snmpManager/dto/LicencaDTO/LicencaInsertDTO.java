package com.example.snmpManager.dto.LicencaDTO;

import com.example.snmpManager.entities.LicencaEntity;
import com.example.snmpManager.entities.enums.TipoLicenca;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class LicencaInsertDTO implements Serializable {
    private Long id;
    private String nome;
    private String fabricante;
    private String modelo;
    private String descricao;
    private String numeroSerie;
    private Date dtAquisicao;
    private Date dtVencimentoGarantia;
    private Date dtExpiracao;
    private BigDecimal valorCompra;
    private String fornecedor;
    private String observacao;
    private String software;
    private Integer qtdLicencas;
    private String chaveLicenca;

    private TipoLicenca tipo;

    public LicencaInsertDTO() {
    }

    public LicencaInsertDTO(LicencaEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.fabricante = entity.getFabricante();
        this.modelo = entity.getModelo();
        this.descricao = entity.getDescricao();
        this.numeroSerie = entity.getNumeroSerie();
        this.dtAquisicao = entity.getDtAquisicao();
        this.dtVencimentoGarantia = entity.getDtVencimentoGarantia();
        this.dtExpiracao = entity.getDtExpiracao();
        this.valorCompra = entity.getValorCompra();
        this.fornecedor = entity.getFornecedor();
        this.observacao = entity.getObservacao();
        this.software = entity.getSoftware();
        this.qtdLicencas = entity.getQtdLicencas();
        this.chaveLicenca = entity.getChaveLicenca();
        this.tipo = entity.getTipo();
    }
}
