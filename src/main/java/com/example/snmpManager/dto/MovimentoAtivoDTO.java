package com.example.snmpManager.dto;

import com.example.snmpManager.entities.MovimentoAtivoEntity;
import com.example.snmpManager.entities.StatusAtivo;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MovimentoAtivoDTO implements Serializable {

    private Long id;
    private String descricao;
    private Date dtMovimento;
    private StatusAtivo statusAtivoAnterior;
    private StatusAtivo statusAtivo;

    private AtivoDTO ativo;
    private UsuarioDTO usuario;

    private List<AnexoDTO> anexos = new ArrayList<>();

    public MovimentoAtivoDTO(MovimentoAtivoEntity entity) {
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
