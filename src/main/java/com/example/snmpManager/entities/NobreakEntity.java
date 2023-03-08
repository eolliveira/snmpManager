package com.example.snmpManager.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue(value = "NOBREAK")
public class NobreakEntity extends AtivoEntity {
    private String potencialNominal;
    private String tensaoEntrada;
    private String tensaoSaida;

}
