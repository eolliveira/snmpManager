package com.example.snmpManager.entities;

import com.example.snmpManager.dto.LicencaDTO.LicencaDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@DiscriminatorValue(value = "LICENCA")
public class LicencaEntity extends AtivoEntity {
    private String software;
    private Integer qtdLicencas;
    private String chaveLicenca;
    @Enumerated(EnumType.STRING)
    private TipoLicenca tipo;

    //ativo a qual licenca é atribuida

    //TODO(fazer relacionamento muitos para muitos licenca e ativo)
    @ManyToOne
    @JoinColumn(name = "ID_ATIVO_LICENCA")
    private AtivoEntity ativo;

    public LicencaEntity() {}

    public LicencaEntity(LicencaDTO dto) {
        this.setStatus(StatusAtivo.DISPONIVEL);
        this.setInativo(false);
        this.software = dto.getSoftware();
        this.qtdLicencas = dto.getQtdLicencas();
        this.chaveLicenca = dto.getChaveLicenca();
        this.tipo = dto.getTipo();
    }
}
