package com.example.snmpManager.objects.EstacaoTrabalhoObjects;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class HardDiskObject implements Serializable {
    private String nome;
    private String modelo;
    private String numeroSerie;
    private String capacidade;
    private List<PartitionObject> particoes = new ArrayList<>();
}
