package com.example.snmpManager.dto.MotivoAtivoDTO;

import com.example.snmpManager.dto.AnexoDTO.AnexoDTO;
import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.entities.MovimentoEntity;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MovimentoAtivoDTO implements Serializable {
    private Long id;
    private String descricao;
    private Instant dtMovimento;
    private StatusAtivo statusAtivoAnterior;
    private StatusAtivo statusAtivo;

    private AtivoDTO ativo;
    private UsuarioDTO usuario;

    private List<AnexoDTO> anexos = new ArrayList<>();

    public MovimentoAtivoDTO() {
    }

    public MovimentoAtivoDTO(MovimentoEntity entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.dtMovimento = entity.getDtMovimento();
        this.statusAtivoAnterior = entity.getStatusAtivoAnterior();
        this.statusAtivo = entity.getStatusAtivo();
        this.ativo = new AtivoDTO(entity.getAtivo());
        this.usuario = new UsuarioDTO(entity.getUsuario());

        entity.getAnexos().stream().map(anexo -> this.anexos.add(new AnexoDTO(anexo))).collect(Collectors.toList());
    }
}