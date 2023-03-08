package com.example.snmpManager.dto.ImpressoraDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.entities.ImpressoraEntity;

import java.io.Serializable;

public class ImpressoraDTO extends AtivoDTO implements Serializable {

    private String enderecoIp;
    private String mascaraSubRede;
    private String enderecoMac;
    private String gateway;
    private String tempoLigado;
    private String dominio;
    private String totalImpressoes;

}
