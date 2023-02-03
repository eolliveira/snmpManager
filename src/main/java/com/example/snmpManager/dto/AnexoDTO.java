package com.example.snmpManager.dto;

import com.example.snmpManager.entities.AnexoEntity;
import lombok.Data;
@Data
public class AnexoDTO {
    private Long id;
    private UsuarioDTO movimento;

    public AnexoDTO(AnexoEntity entity) {
        this.id = entity.getId();
        this.movimento = new UsuarioDTO(entity.getMovimento());
    }
}
