package com.example.snmpManager.dto.FinanceiroDTO;

import com.example.snmpManager.entities.AnexoEntity;
import com.example.snmpManager.entities.AtivoEntity;
import com.example.snmpManager.entities.FinanceiroEntity;
import com.example.snmpManager.entities.UsuarioEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class FinanceiroDTO implements Serializable {

    private Long id;
    private String descricao;
    private Instant dtFinanceiro;
    private AtivoEntity ativo;
    private UsuarioEntity usuario;
    private List<AnexoEntity> anexos;

    public FinanceiroDTO(FinanceiroEntity entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.dtFinanceiro = entity.getDtFinanceiro();
        this.ativo = entity.getAtivo();
        this.usuario = entity.getUsuario();

        //TODO(Não adicionando finanças do ativo)
    }
}
