package com.example.snmpManager.entities;

import com.example.snmpManager.dto.LicencaDTO.LicencaDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@DiscriminatorValue(value = "LICENCA")
public class LicencaEntity extends AtivoEntity {
    private String software;
    private Integer qtdLicencas;
    private String chaveLicenca;
    @Enumerated(EnumType.STRING)
    private TipoLicenca tipo;

    @ManyToMany(mappedBy = "licencas")
    private final Set<AtivoEntity> products = new HashSet<>();


    public LicencaEntity() {}

    public LicencaEntity(LicencaDTO dto) {
        this.setId(dto.getId());
        this.setNome(dto.getNome());
        this.setDescricao(dto.getDescricao());
        this.setFabricante(dto.getFabricante());
        this.setNumeroSerie(dto.getNumeroSerie());
        this.setModelo(dto.getModelo());
        this.setDtAquisicao(dto.getDtAquisicao());
        this.setDtVencimentoGarantia(dto.getDtVencimentoGarantia());
        this.setDtExpiracao(dto.getDtExpiracao());
        this.setUltimoSincronismo(dto.getUltimoSincronismo());
        this.setValorCompra(dto.getValorCompra());
        this.setFornecedor(dto.getFornecedor());
        this.setObeservacao(getObeservacao());
        this.setStatus(StatusAtivo.DISPONIVEL);
        this.setInativo(false);
        this.software = dto.getSoftware();
        this.qtdLicencas = dto.getQtdLicencas();
        this.chaveLicenca = dto.getChaveLicenca();
        this.tipo = dto.getTipo();
    }
}
