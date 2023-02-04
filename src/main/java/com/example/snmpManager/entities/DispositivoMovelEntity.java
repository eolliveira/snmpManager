package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue(value = "DISPOSITIVOMOVEL")
public class DispositivoMovelEntity extends AtivoEntity {
    private String versaoAndroid;
}