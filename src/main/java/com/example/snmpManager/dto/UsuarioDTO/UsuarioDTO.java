package com.example.snmpManager.dto.UsuarioDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoAtivoDTO;
import com.example.snmpManager.entities.UsuarioEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UsuarioDTO implements Serializable {
    private Long id;
    private List<AtivoDTO> ativos = new ArrayList<>();
    private List<MovimentoAtivoDTO> movimentosAtivo = new ArrayList<>();

    public UsuarioDTO() {
    }

    public UsuarioDTO(UsuarioEntity entity) {
        this.id = entity.getId();

        entity.getAtivos().stream().map(ativo -> this.ativos.add(new AtivoDTO(ativo))).collect(Collectors.toList());
        entity.getMovimentosAtivo().stream().map(movimento -> this.movimentosAtivo.add(new MovimentoAtivoDTO(movimento))).collect(Collectors.toList());
    }
}
