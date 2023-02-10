package com.example.snmpManager.dto.LicencaDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoAtivoDTO;
import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.entities.LicencaEntity;
import com.example.snmpManager.entities.StatusAtivo;
import com.example.snmpManager.entities.TipoLicenca;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class LicencaDTO implements Serializable {
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
    private String observacao;
    private String software;
    private Integer qtdLicencas;
    private String chaveLicenca;

    private UsuarioDTO usuario;

    private List<MovimentoAtivoDTO> movimentos = new ArrayList<>();

    private List<LicencaDTO> licencas = new ArrayList<>();
    private TipoLicenca tipo;
    private AtivoDTO ativo;

    public LicencaDTO() {
    }

    public LicencaDTO(LicencaEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.fabricante = entity.getFabricante();
        this.modelo = entity.getModelo();
        this.descricao = entity.getDescricao();
        this.numeroSerie = entity.getNumeroSerie();
        this.inativo = entity.getInativo();
        this.status = entity.getStatus();
        this.dtAquisicao = entity.getDtAquisicao();
        this.dtVencimentoGarantia = entity.getDtVencimentoGarantia();
        this.dtExpiracao = entity.getDtExpiracao();
        this.ultimoSincronismo = entity.getUltimoSincronismo();
        this.valorCompra = entity.getValorCompra();
        this.fornecedor = entity.getFornecedor();
        this.observacao = entity.getObeservacao();
        this.software = entity.getSoftware();
        this.qtdLicencas = entity.getQtdLicencas();
        this.chaveLicenca = entity.getChaveLicenca();
        this.tipo = entity.getTipo();
    }
}
