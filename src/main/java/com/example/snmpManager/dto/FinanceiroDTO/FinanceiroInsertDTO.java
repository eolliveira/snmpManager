package com.example.snmpManager.dto.FinanceiroDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.entities.FinanceiroEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class FinanceiroInsertDTO implements Serializable {

    private Long id;
    private String descricao;
    private Double valor;
    private Instant dtFinanceiro;
    private AtivoDTO ativo;
    private UsuarioDTO usuario;

    //TODO(ANEXO)

    public FinanceiroInsertDTO(FinanceiroEntity entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.valor = entity.getValor();
        this.dtFinanceiro = entity.getDtFinanceiro();
    }
}
