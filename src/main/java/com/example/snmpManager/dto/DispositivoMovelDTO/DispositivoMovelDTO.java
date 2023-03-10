package com.example.snmpManager.dto.DispositivoMovelDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.entities.DispositivoMovelEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DispositivoMovelDTO extends AtivoDTO implements Serializable {
    private String versaoAndroid;

    public DispositivoMovelDTO(DispositivoMovelEntity entity) {
        this.setId(entity.getId());
        this.setNome(entity.getNome());
        this.setFabricante(entity.getFabricante());
        this.setModelo(entity.getModelo());
        this.setDescricao(entity.getDescricao());
        this.setNumeroSerie(entity.getNumeroSerie());
        this.setInativo(entity.getInativo());
        this.setStatus(entity.getStatus());
        this.setDtAquisicao(entity.getDtAquisicao());
        this.setDtVencimentoGarantia(entity.getDtVencimentoGarantia());
        this.setDtExpiracao(entity.getDtExpiracao());
        this.setUltimoSincronismo(entity.getUltimoSincronismo());
        this.setValorCompra(entity.getValorCompra());
        this.setFornecedor(entity.getFornecedor());
        this.setObservacao(entity.getObservacao());
        this.setTempoLigado(entity.getTempoLigado());
        this.setNomeHost(entity.getNomeHost());
        this.setDominio(entity.getDominio());
        this.setGateway(entity.getGateway());
        this.versaoAndroid = entity.getVersaoAndroid();
    }
}
