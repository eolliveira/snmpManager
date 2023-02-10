package com.example.snmpManager.objects.EstacaoTrabalho;

import lombok.Data;

import java.io.Serializable;

@Data
public class PartitionObject implements Serializable {
    private String pontoMontagem;
    private String capacidade;
}
