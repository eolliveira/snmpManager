package com.example.snmpManager.dto;

import com.example.snmpManager.entities.AtivoEntity;

import java.io.Serializable;

public class DispositivoMovelDTO extends AtivoDTO implements Serializable {
    private String versaoAndroid;

    public DispositivoMovelDTO(AtivoEntity entity) {
        super(entity);
    }
}
