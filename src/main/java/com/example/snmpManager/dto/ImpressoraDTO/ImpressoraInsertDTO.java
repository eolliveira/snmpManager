package com.example.snmpManager.dto.ImpressoraDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.entities.ImpressoraEntity;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ImpressoraInsertDTO extends AtivoDTO implements Serializable {

    private String nome;
    private String nomeHost;
    private String numeroSerie;
    private String enderecoIp;
    private String mascaraSubRede;
    private String enderecoMac;
    private String gateway;
    private String tempoLigado;
    private String dominio;
    private String totalImpressoes;

    public ImpressoraInsertDTO(ImpressoraEntity impressora) {
        this.setId(impressora.getId());
        this.setNome(impressora.getNome());
        this.setNomeHost(impressora.getNomeHost());
        this.setDescricao(impressora.getDescricao());
        this.setDominio(impressora.getDominio());
        this.setInativo(false);
        this.setStatus(StatusAtivo.DISPONIVEL);
        this.setNumeroSerie(impressora.getNumeroSerie());
        this.setModelo(impressora.getModelo());
        this.setDtAquisicao(impressora.getDtAquisicao());
        this.setDtVencimentoGarantia(impressora.getDtVencimentoGarantia());
        this.setDtExpiracao(impressora.getDtExpiracao());
        this.setUltimoSincronismo(impressora.getUltimoSincronismo());
        this.setValorCompra(impressora.getValorCompra());
        this.setFornecedor(impressora.getFornecedor());
        this.setObservacao(impressora.getObservacao());
        this.setGateway(impressora.getGateway());
        this.totalImpressoes = impressora.getTotalImpressoes();
        this.tempoLigado = impressora.getTempoLigado();
    }
}
