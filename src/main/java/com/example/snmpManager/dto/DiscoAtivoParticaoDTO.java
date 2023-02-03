package com.example.snmpManager.dto;

import com.example.snmpManager.entities.DiscoAtivoParticaoEntity;
import lombok.Data;

@Data
public class DiscoAtivoParticaoDTO {
    private Long id;
    private String pontoMontagem;
    private String capacidade;
    private DiscoAtivoDTO disco;

    public DiscoAtivoParticaoDTO(DiscoAtivoParticaoEntity entity) {
        this.id = entity.getId();
        this.pontoMontagem = entity.getPontoMontagem();
        this.capacidade = entity.getCapacidade();
        this.disco = new DiscoAtivoDTO(entity.getDisco());
    }
}
