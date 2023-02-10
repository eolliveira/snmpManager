package com.example.snmpManager.dto.AtivoDTO;

import com.example.snmpManager.dto.LicencaDTO.LicencaDTO;
import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoAtivoDTO;
import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.entities.AtivoEntity;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private Boolean inativo;
    private StatusAtivo status;
    private Date dtAquisicao;
    private Date dtVencimentoGarantia;
    private Date dtExpiracao;
    private Date ultimoSincronismo;
    private BigDecimal valorCompra;
    private String fornecedor;
    private String observacao;

    private UsuarioDTO usuario;

    private List<MovimentoAtivoDTO> movimentos = new ArrayList<>();
    private List<LicencaDTO> licencas = new ArrayList<>();

    public AtivoDTO(){}

    public AtivoDTO(AtivoEntity entity) {
        //TODO(VERIFICAR SE É NECESSARIO ESSE CONSTRUTOR)
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
        //this.usuario = new UsuarioDTO(entity.getUsuario());

        //entity.getMovimentos().stream().map(m -> this.movimentos.add(new MovimentoAtivoDTO(m))).collect(Collectors.toList());
        //entity.getLicencas().stream().map(l -> this.licencas.add(new LicencaDTO(l))).collect(Collectors.toList());
    }
}