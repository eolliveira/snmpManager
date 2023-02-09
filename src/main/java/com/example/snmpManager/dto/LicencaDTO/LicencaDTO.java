package com.example.snmpManager.dto.LicencaDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.entities.LicencaEntity;
import com.example.snmpManager.entities.TipoLicenca;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
public class LicencaDTO extends AtivoDTO implements Serializable {
    private String software;
    private Integer qtdLicencas;
    private String chaveLicenca;
    private TipoLicenca tipo;
    private AtivoDTO ativo;

    public LicencaDTO() {}

    public LicencaDTO(LicencaEntity entity) {
        this.setNome(entity.getNome());
        this.setFabricante(entity.getFabricante());
        this.setModelo(entity.getModelo());
        this.setDescricao(entity.getDescricao());
        this.setNumeroSerie(entity.getNumeroSerie());
        this.setInativo(entity.getInativo());
        this.setStatus(entity.getStatus());
        this.setDtAquisicao(entity.getDtAquisicao());
        this.setDtVencimentoGarantia(entity.getDtVencimentoGarantia());
        this.setDtExpiracao(entity.getDtExpiracao());
        this.setUltimoSincronismo(entity.getUltimoSincronismo());
        this.setValorCompra(entity.getValorCompra());
        this.setFornecedor(entity.getFornecedor());
        this.setObeservacao(entity.getObeservacao());
        this.software = entity.getSoftware();
        this.qtdLicencas = entity.getQtdLicencas();
        this.chaveLicenca = entity.getChaveLicenca();
        this.tipo = entity.getTipo();
    }
}
