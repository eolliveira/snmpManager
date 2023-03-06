package com.example.snmpManager.entities;

import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraInsertDTO;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue(value = "IMPRESSORA")
public class ImpressoraEntity extends AtivoEntity {
    private String tempoLigado;
    private String totalImpressoes;

    public ImpressoraEntity() {}

    public ImpressoraEntity(ImpressoraInsertDTO dto) {
    }

}
