package com.example.snmpManager.dto.FinanceiroDTO;

import com.example.snmpManager.dto.AnexoDTO.AnexoDTO;
import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FinanceiroInsertDTO implements Serializable {

    private Long id;
    @NotBlank(message = "Campo Requerido")
    private String descricao;
    @Positive(message = "Valor deve ser positivo")
    private Double valor;
    private Instant dtFinanceiro;
    @NotNull
    private AtivoDTO ativo;
    @NotNull
    private UsuarioDTO usuario;
    private List<AnexoDTO> anexos = new ArrayList<>();
}
