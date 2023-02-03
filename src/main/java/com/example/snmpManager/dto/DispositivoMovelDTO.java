package com.example.snmpManager.dto;

import com.example.snmpManager.entities.AtivoEntity;

public class DispositivoMovelDTO extends AtivoDTO {
    private String versaoAndroid;

    public DispositivoMovelDTO(AtivoEntity entity) {
        super(entity);
    }
}
