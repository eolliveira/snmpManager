package com.example.snmpManager.dto.FinanceiroDTO;

import com.example.snmpManager.dto.AnexoDTO.AnexoDTO;
import com.example.snmpManager.entities.FinanceiroEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FinanceiroDTO implements Serializable {

    private Long id;
    private String descricao;
    private Double valor;
    private Instant dtFinanceiro;

    private List<AnexoDTO> anexos = new ArrayList<>();

    public FinanceiroDTO(FinanceiroEntity entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.valor = entity.getValor();
        this.dtFinanceiro = entity.getDtFinanceiro();
    }
}
