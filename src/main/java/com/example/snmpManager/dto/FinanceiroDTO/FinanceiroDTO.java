package com.example.snmpManager.dto.FinanceiroDTO;

import com.example.snmpManager.entities.FinanceiroEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class FinanceiroDTO implements Serializable {

    private Long id;
    private String descricao;
    private Double valor;
    private Instant dtFinanceiro;

    //TODO(Implementar add anexo)

    public FinanceiroDTO(FinanceiroEntity entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.valor = entity.getValor();
        this.dtFinanceiro = entity.getDtFinanceiro();
    }
}
