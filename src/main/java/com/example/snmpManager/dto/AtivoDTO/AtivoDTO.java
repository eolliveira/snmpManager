package com.example.snmpManager.dto.AtivoDTO;

import com.example.snmpManager.dto.InterfaceDTO.InterfaceDTO;
import com.example.snmpManager.dto.LicencaDTO.LicencaInsertDTO;
import com.example.snmpManager.dto.MovimentoDTO.MovimentoDTO;
import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.entities.AnexoEntity;
import com.example.snmpManager.entities.AtivoEntity;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AtivoDTO implements Serializable {

    private Long id;
    private String nome;
    private String fabricante;
    private String modelo;
    private String descricao;
    private String numeroSerie;
    private String nomeHost;
    private Boolean inativo;
    private String dominio;
    private String tempoLigado;
    private StatusAtivo status;
    private String gateway;
    private Date dtAquisicao;
    private Date dtVencimentoGarantia;
    private Date dtExpiracao;
    private Instant ultimoSincronismo;
    private BigDecimal valorCompra;
    private String fornecedor;
    private String observacao;
    private UsuarioDTO usuario;
    private List<MovimentoDTO> movimentos = new ArrayList<>();
    private List<LicencaInsertDTO> licencas = new ArrayList<>();
    private List<InterfaceDTO> interfaces = new ArrayList<>();
    private List<AnexoEntity> anexos = new ArrayList<>();

    public AtivoDTO() {}

    public AtivoDTO(AtivoEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.fabricante = entity.getFabricante();
        this.modelo = entity.getModelo();
        this.dominio = entity.getDominio();
        this.descricao = entity.getDescricao();
        this.tempoLigado = entity.getTempoLigado();
        this.numeroSerie = entity.getNumeroSerie();
        this.inativo = entity.getInativo();
        this.status = entity.getStatus();
        this.gateway = entity.getGateway();
        this.dtAquisicao = entity.getDtAquisicao();
        this.dtVencimentoGarantia = entity.getDtAquisicao();
        this.dtExpiracao = entity.getDtExpiracao();
        this.ultimoSincronismo = entity.getUltimoSincronismo();
        this.valorCompra = entity.getValorCompra();
        this.fornecedor = entity.getFornecedor();
        this.observacao = entity.getObservacao();
    }
}
