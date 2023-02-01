package com.example.snmpManager.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue(value = "DISPOSITIVOMOVEL")
public class DispositivoMovel extends AtivoEntity {
    private String versaoAndroid;
}
