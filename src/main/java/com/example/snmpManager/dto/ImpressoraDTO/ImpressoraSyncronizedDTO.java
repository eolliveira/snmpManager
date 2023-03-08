package com.example.snmpManager.dto.ImpressoraDTO;

import com.example.snmpManager.objects.PrinterObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImpressoraSyncronizedDTO implements Serializable {
    private String nome;
    private String nomeHost;
    private String modelo;
    private String numeroSerie;
    private String enderecoIp;
    private String mascaraSubRede;
    private String enderecoMac;
    private String gateway;
    private String tempoLigado;
    private String dominio;
    private String totalImpressoes;

    public ImpressoraSyncronizedDTO(PrinterObject objAgent) {
        this.nomeHost = objAgent.getNomeHost();
        this.modelo = objAgent.getModelo();
        this.numeroSerie = objAgent.getNumeroSerie();
        this.enderecoIp = objAgent.getEnderecoIp();
        this.mascaraSubRede = objAgent.getMascaraSubRede();
        this.enderecoMac = objAgent.getEnderecoMac();
        this.gateway = objAgent.getGateway();
        this.tempoLigado = objAgent.getTempoLigado();
        this.dominio = objAgent.getDominio();
        this.totalImpressoes = objAgent.getTotalImpressoes();
    }
}
