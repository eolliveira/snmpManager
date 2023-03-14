package com.example.snmpManager.dto.ImpressoraDTO;

import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.entities.ImpressoraEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class ImpressoraUpdateDTO implements Serializable {

    private Long id;
    private String nome;
    private String modelo;
    private String descricao;
    private String numeroSerie;
    private String nomeHost;
    private Boolean inativo;
    private Date dtAquisicao;
    private Date dtVencimentoGarantia;
    private Date dtExpiracao;
    private BigDecimal valorCompra;
    private String fornecedor;
    private String observacao;
    private UsuarioDTO usuario;

    public ImpressoraUpdateDTO(ImpressoraEntity impressora) {
        this.setId(impressora.getId());
        this.setNome(impressora.getNome());
        this.setNomeHost(impressora.getNomeHost());
        this.setDescricao(impressora.getDescricao());
        this.setInativo(impressora.getInativo());
        this.setNumeroSerie(impressora.getNumeroSerie());
        this.setModelo(impressora.getModelo());
        this.setDtAquisicao(impressora.getDtAquisicao());
        this.setDtVencimentoGarantia(impressora.getDtVencimentoGarantia());
        this.setDtExpiracao(impressora.getDtExpiracao());
        this.setValorCompra(impressora.getValorCompra());
        this.setFornecedor(impressora.getFornecedor());
        this.setObservacao(impressora.getObservacao());
    }
}
