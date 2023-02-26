package com.example.snmpManager.dto.MotivoAtivoDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class MovimentoInsertDTO implements Serializable {
    private Long id;
    private String descricao;
    private Instant dtMovimento;
    private StatusAtivo statusAtivoAnterior;
    private StatusAtivo statusAtivo;
    private AtivoDTO ativo;
    private UsuarioDTO usuario;
}
