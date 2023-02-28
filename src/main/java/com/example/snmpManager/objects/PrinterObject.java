package com.example.snmpManager.objects;

import lombok.Data;

@Data
public class PrinterObject {

    private String nome;
    private String modelo;
    private String numeroSerie;
    private String enderecoMac;
    private String tempoLigado;
    private String totalImpressoes;

}
