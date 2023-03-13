package com.example.snmpManager.dto.CustoDTO;

import com.example.snmpManager.dto.AnexoDTO.AnexoDTO;
import com.example.snmpManager.entities.CustoEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CustoDTO implements Serializable {

    private Long id;
    private String descricao;
    private Double valor;
    private Instant dtCusto;

    private List<AnexoDTO> anexos = new ArrayList<>();

    public CustoDTO(CustoEntity entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.valor = entity.getValor();
        this.dtCusto = entity.getDtCusto();
    }
}
