package com.example.snmpManager.dto.MotivoAtivoDTO;

import com.example.snmpManager.entities.MovimentoEntity;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class MovimentoDTO implements Serializable {
    private Long id;
    private String descricao;
    private Instant dtMovimento;
    private StatusAtivo statusAtivoAnterior;
    private StatusAtivo statusAtivo;

    public MovimentoDTO(MovimentoEntity entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.dtMovimento = entity.getDtMovimento();
        this.statusAtivoAnterior = entity.getStatusAtivoAnterior();
        this.statusAtivo = entity.getStatusAtivo();
    }
}
