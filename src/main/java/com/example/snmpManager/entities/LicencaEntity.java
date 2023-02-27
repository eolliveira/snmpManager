package com.example.snmpManager.entities;

import com.example.snmpManager.dto.LicencaDTO.LicencaInsertDTO;
import com.example.snmpManager.entities.enums.StatusAtivo;
import com.example.snmpManager.entities.enums.TipoLicenca;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue(value = "LICENCA")
public class LicencaEntity extends AtivoEntity {
    private String software;
    private Integer qtdLicencas;
    private String chaveLicenca;
    @Enumerated(EnumType.STRING)
    private TipoLicenca tipo;

    @ManyToMany(mappedBy = "licencas")
    private final Set<AtivoEntity> products = new HashSet<>();
    //TODO(produstos ?)


    public LicencaEntity() {}

    public LicencaEntity(LicencaInsertDTO dto) {
        this.setId(dto.getId());
        this.setNome(dto.getNome());
        this.setDescricao(dto.getDescricao());
        this.setFabricante(dto.getFabricante());
        this.setNumeroSerie(dto.getNumeroSerie());
        this.setModelo(dto.getModelo());
        this.setDtAquisicao(dto.getDtAquisicao());
        this.setDtVencimentoGarantia(dto.getDtVencimentoGarantia());
        this.setDtExpiracao(dto.getDtExpiracao());
        this.setValorCompra(dto.getValorCompra());
        this.setFornecedor(dto.getFornecedor());
        this.setObservacao(dto.getObservacao());
        this.setStatus(StatusAtivo.DISPONIVEL);
        this.setInativo(false);
        this.software = dto.getSoftware();
        this.qtdLicencas = dto.getQtdLicencas();
        this.chaveLicenca = dto.getChaveLicenca();
        this.tipo = dto.getTipo();
    }

}
