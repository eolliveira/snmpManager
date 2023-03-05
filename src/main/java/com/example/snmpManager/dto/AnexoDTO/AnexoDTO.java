package com.example.snmpManager.dto.AnexoDTO;

import com.example.snmpManager.entities.AnexoEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class AnexoDTO implements Serializable {
    private Long id;

    public AnexoDTO(AnexoEntity entity) {
        this.id = entity.getId();
    }
}
