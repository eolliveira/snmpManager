package com.example.snmpManager.dto.EstacaoTrabalhoDTO;

import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class EstacaoTrabalhoUpdateDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String nomeHost;
    private Boolean inativo;
    private Date dtAquisicao;
    private Date dtVencimentoGarantia;
    private Date dtExpiracao;
    private BigDecimal valorCompra;
    private String fornecedor;
    private String observacao;
    private UsuarioDTO usuario;

    public EstacaoTrabalhoUpdateDTO(EstacaoTrabalhoEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.inativo = entity.getInativo();
        this.dtAquisicao = entity.getDtAquisicao();
        this.dtVencimentoGarantia = entity.getDtVencimentoGarantia();
        this.dtExpiracao = entity.getDtExpiracao();
        this.valorCompra = entity.getValorCompra();
        this.fornecedor = entity.getFornecedor();
        this.observacao = entity.getObservacao();
        this.nomeHost = entity.getNomeHost();
    }


}
