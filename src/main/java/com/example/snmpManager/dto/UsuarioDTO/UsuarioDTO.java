package com.example.snmpManager.dto.UsuarioDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.dto.MovimentoDTO.MovimentoDTO;
import com.example.snmpManager.entities.UsuarioEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    private Long id;
    private List<AtivoDTO> ativos = new ArrayList<>();
    private List<MovimentoDTO> movimentosAtivo = new ArrayList<>();

    public UsuarioDTO(UsuarioEntity entity) {
        this.id = entity.getId();
    }
}
