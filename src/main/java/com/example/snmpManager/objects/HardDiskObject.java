package com.example.snmpManager.objects;

import lombok.Data;

import java.io.Serializable;

@Data
public class HardDiskObject implements Serializable {
    private String nome;
    private String modelo;
    private String numeroSerie;
    private String capacidade;
    private String usado;
    private String disponivel;
}
