package com.example.snmpManager.dto.DispositivoMovelDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.entities.AtivoEntity;

import java.io.Serializable;

public class DispositivoMovelDTO extends AtivoDTO implements Serializable {
    private String versaoAndroid;

    public DispositivoMovelDTO(AtivoEntity entity) {
        super(entity);
    }
}
