package com.example.snmpManager.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue(value = "DISPOSITIVOMOVEL")
public class DispositivoMovelEntity extends AtivoEntity {
    private String versaoAndroid;
}
