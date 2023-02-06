package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@DiscriminatorValue(value = "LICENCA")
public class LicencaEntity extends AtivoEntity {
    private String software;
    private Integer qtdLicencas;
    private String chaveLicenca;
    private TipoLicenca tipo;

    //ativo a qual licenca é atribuida

    @ManyToOne
    @JoinColumn(name = "ID_ATIVO_LICENCA")
    private AtivoEntity ativo;

}
