package com.example.snmpManager.objects;

import lombok.Data;

@Data
public class HardDiskObject {
    private String nome;
    private String modelo;
    private String numeroSerie;
    private String capacidade;
    private String usado;
    private String disponivel;
}
