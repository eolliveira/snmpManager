package com.example.snmpManager.dto.DiscoParticaoDTO;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.entities.DiscoParticaoEntity;
import com.example.snmpManager.objects.EstacaoTrabalho.PartitionObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class DiscoParticaoDTO implements Serializable {
    private Long id;
    private String pontoMontagem;
    private String capacidade;
    private DiscoDTO disco;

    public DiscoParticaoDTO() {
    }

    public DiscoParticaoDTO(DiscoParticaoEntity entity) {
        this.id = entity.getId();
        this.pontoMontagem = entity.getPontoMontagem();
        this.capacidade = entity.getCapacidade();
        this.disco = new DiscoDTO(entity.getDisco());
    }

    public DiscoParticaoDTO(PartitionObject obj) {
        this.pontoMontagem = obj.getPontoMontagem();
        this.capacidade = obj.getCapacidade();
    }
}
