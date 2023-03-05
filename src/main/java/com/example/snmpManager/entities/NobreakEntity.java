package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue(value = "NOBREAK")
public class NobreakEntity extends AtivoEntity {
    private String potencialNominaal;
    private String tensaoEntrada;
    private String tensaoSaida;

}
