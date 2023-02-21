package com.example.snmpManager.objects;

import lombok.Data;

@Data
public class TrapObject {
    private String descricao;;
    private String tipoAtivo;
    private String ipAddress;
    private String instante;
    public TrapObject(String descricao, String tipoAtivo, String ipAddress, String instante) {
       this.descricao = descricao;
       this.tipoAtivo = tipoAtivo;
       this.ipAddress = ipAddress;
       this.instante = instante;
    }
}
