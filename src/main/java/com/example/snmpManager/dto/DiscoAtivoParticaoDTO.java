package com.example.snmpManager.dto;

import com.example.snmpManager.entities.DiscoAtivoParticaoEntity;
import com.example.snmpManager.objects.PartitionObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class DiscoAtivoParticaoDTO implements Serializable {
    private Long id;
    private String pontoMontagem;
    private String capacidade;
    private DiscoAtivoDTO disco;

    public DiscoAtivoParticaoDTO() {
    }

    public DiscoAtivoParticaoDTO(DiscoAtivoParticaoEntity entity) {
        this.id = entity.getId();
        this.pontoMontagem = entity.getPontoMontagem();
        this.capacidade = entity.getCapacidade();
        this.disco = new DiscoAtivoDTO(entity.getDisco());
    }

    public DiscoAtivoParticaoDTO(PartitionObject obj) {
        this.pontoMontagem = obj.getPontoMontagem();
        this.capacidade = obj.getCapacidade();
    }
}
