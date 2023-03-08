package com.example.snmpManager.objects;

import lombok.Data;

@Data
public class PrinterObject {

    private String nomeHost;
    private String modelo;
    private String numeroSerie;
    private String enderecoIp;
    private String mascaraSubRede;
    private String enderecoMac;
    private String gateway;
    private String dominio;
    private String tempoLigado;
    private String totalImpressoes;
    private String status;

}
