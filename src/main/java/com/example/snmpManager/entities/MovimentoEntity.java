package com.example.snmpManager.entities;

import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoInsertDTO;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "ATIVOMOVIMENTO")
@Entity
@Data
public class MovimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATIVOMOVIMENTO")
    private Long id;
    private String descricao;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dtMovimento;

    @Enumerated(EnumType.STRING)
    private StatusAtivo statusAtivo;
    @Enumerated(EnumType.STRING)
    private StatusAtivo statusAtivoAnterior;

    @ManyToOne
    @JoinColumn(name = "ID_ATIVO")
    private AtivoEntity ativo;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntity usuario;

    public MovimentoEntity() {
    }
    public MovimentoEntity(MovimentoInsertDTO dto, AtivoEntity ativo, UsuarioEntity usuario) {
        this.id = dto.getId();
        this.descricao = dto.getDescricao();
        this.dtMovimento = dto.getDtMovimento();
        this.statusAtivoAnterior = ativo.getStatus();
        this.statusAtivo = dto.getStatusAtivo();
        this.dtMovimento = Instant.now();
        this.ativo = ativo;
        this.usuario = usuario;
    }
}
