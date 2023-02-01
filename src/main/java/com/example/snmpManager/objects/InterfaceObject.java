package com.example.snmpManager.objects;

import lombok.Data;

import java.io.Serializable;

@Data
public class InterfaceObject implements Serializable {
    private String nomeLocal;
    private String nomeFabricante;
    private String enderecoMac;
    private String enderecoIp;
    private String mascaraSubRede;
}
