package com.example.snmpManager.entities;

import com.example.snmpManager.dto.DispositivoMovelDTO.DispositivoMovelDTO;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue(value = "DISPOSITIVOMOVEL")
public class DispositivoMovelEntity extends AtivoEntity {
    private String versaoAndroid;

    public DispositivoMovelEntity(DispositivoMovelDTO dto) {
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
        this.setGateway(dto.getGateway());
        this.setTempoLigado(dto.getTempoLigado());
        this.setNomeHost(dto.getNomeHost());
        this.versaoAndroid = dto.getVersaoAndroid();
    }

}
