package com.example.snmpManager.dto;

import com.example.snmpManager.entities.AtivoDiscoParticaoEntity;
import com.example.snmpManager.objects.PartitionObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class AtivoDiscoParticaoDTO implements Serializable {
    private Long id;
    private String pontoMontagem;
    private String capacidade;
    private AtivoDiscoDTO disco;

    public AtivoDiscoParticaoDTO() {
    }

    public AtivoDiscoParticaoDTO(AtivoDiscoParticaoEntity entity) {
        this.id = entity.getId();
        this.pontoMontagem = entity.getPontoMontagem();
        this.capacidade = entity.getCapacidade();
        this.disco = new AtivoDiscoDTO(entity.getDisco());
    }

    public AtivoDiscoParticaoDTO(PartitionObject obj) {
        this.pontoMontagem = obj.getPontoMontagem();
        this.capacidade = obj.getCapacidade();
    }
}
