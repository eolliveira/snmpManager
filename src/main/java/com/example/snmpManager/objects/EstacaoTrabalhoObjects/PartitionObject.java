package com.example.snmpManager.objects.EstacaoTrabalhoObjects;

import lombok.Data;

import java.io.Serializable;

@Data
public class PartitionObject implements Serializable {
    private String pontoMontagem;
    private String capacidade;
    private String usado;
}
