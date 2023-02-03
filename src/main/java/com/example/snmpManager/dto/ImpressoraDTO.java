package com.example.snmpManager.dto;

import com.example.snmpManager.entities.ImpressoraEntity;

import java.io.Serializable;

public class ImpressoraDTO extends AtivoDTO implements Serializable {

    public ImpressoraDTO(ImpressoraEntity entity) {
        super(entity);
        //parametros adicionais
    }
}
