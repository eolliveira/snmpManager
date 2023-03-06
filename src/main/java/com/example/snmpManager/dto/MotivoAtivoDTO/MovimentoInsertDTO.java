package com.example.snmpManager.dto.MotivoAtivoDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Data
public class MovimentoInsertDTO implements Serializable {
    private Long id;
    @NotBlank(message = "Campo requerido")
    private String descricao;
    private Instant dtMovimento;
    private StatusAtivo statusAtivoAnterior;
    @NotNull(message = "Campo requerido")
    private StatusAtivo statusAtivo;
    @NotNull(message = "Campo requerido")
    private AtivoDTO ativo;
    @NotNull(message = "Campo requerido")
    private UsuarioDTO usuario;
}
