package com.example.snmpManager.entities;

import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraInsertDTO;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue(value = "IMPRESSORA")
public class ImpressoraEntity extends AtivoEntity {
    private String tempoLigado;
    private String totalImpressoes;
    private String gateway;

    private String dominio;

    public ImpressoraEntity() {}

    public ImpressoraEntity(ImpressoraInsertDTO dto) {
        this.setNome(dto.getNome());
        this.setDescricao(dto.getDescricao());
        this.setInativo(false);
        this.setStatus(StatusAtivo.DISPONIVEL);
        this.setFabricante(dto.getFabricante());
        this.setNumeroSerie(dto.getNumeroSerie());
        this.setModelo(dto.getModelo());
        this.setDtAquisicao(dto.getDtAquisicao());
        this.setDtVencimentoGarantia(dto.getDtVencimentoGarantia());
        this.setDtExpiracao(dto.getDtExpiracao());
        this.setUltimoSincronismo(dto.getUltimoSincronismo());
        this.setValorCompra(dto.getValorCompra());
        this.setFornecedor(dto.getFornecedor());
        this.setObservacao(dto.getObservacao());
        this.setDominio(dto.getDominio());
        this.gateway = dto.getGateway();
        this.totalImpressoes = dto.getTotalImpressoes();
        this.tempoLigado = dto.getTempoLigado();
    }

}
