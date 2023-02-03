package com.example.snmpManager.dto;

import com.example.snmpManager.entities.NobreakEntity;

import java.io.Serializable;

public class NobreakDTO extends AtivoDTO implements Serializable {

    public NobreakDTO(NobreakEntity entity) {
        super(entity);
        //parametros adicionais
    }
}
