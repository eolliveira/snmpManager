package com.example.snmpManager.dto.ImpressoraDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImpressoraInsertDTO extends AtivoDTO implements Serializable {
    private String tempoLigado;
    private String totalImpressoes;
}
