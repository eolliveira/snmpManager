package com.example.snmpManager.dto.FinanceiroDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import com.example.snmpManager.entities.FinanceiroEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class FinanceiroInsertDTO implements Serializable {

    private Long id;
    @NotBlank(message = "Campo Requerido")
    private String descricao;
    @Positive(message = "Valor deve ser positivo")
    private Double valor;
    private Instant dtFinanceiro;

    @NonNull
    private AtivoDTO ativo;

    @NotNull
    private UsuarioDTO usuario;

    //TODO(ANEXO)

    public FinanceiroInsertDTO(FinanceiroEntity entity) {
        this.id = entity.getId();
        this.descricao = entity.getDescricao();
        this.valor = entity.getValor();
        this.dtFinanceiro = entity.getDtFinanceiro();
    }
}
