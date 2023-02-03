package com.example.snmpManager.dto;

import com.example.snmpManager.entities.LicencaEntity;
import com.example.snmpManager.entities.TipoLicenca;
import lombok.*;

@Getter
@Setter
public class LicencaDTO extends AtivoDTO {
    private String software;
    private Integer qtdLicencas;
    private String chaveLicenca;
    private TipoLicenca tipo;

    private AtivoDTO ativo;

    public LicencaDTO(LicencaEntity entity) {
        super(entity);
        this.software = entity.getSoftware();
        this.qtdLicencas = entity.getQtdLicencas();
        this.chaveLicenca = entity.getChaveLicenca();
        this.tipo = entity.getTipo();
        this.ativo = new AtivoDTO(entity.getAtivo());
    }
}
