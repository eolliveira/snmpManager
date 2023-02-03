package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorValue(value = "ESTACAOTRABALHO")
public class EstacaoTrabalhoEntity extends AtivoEntity {
    private String sistemaOperacional;
    private String processador;
    private String arquiteturaSo;
    private String memoriaRam;
    private String nomeHost;
    private String ultimoUsuarioLogado;
    private String dominio;
    private String dnsList;
    private String gateway;

    @OneToMany(mappedBy = "estacaoTrabalho")
    private List<InterfaceAtivoEntity> interfaces = new ArrayList<>();

    @OneToMany(mappedBy = "estacaoTrabalho")
    private List<DiscoAtivoEntity> discos = new ArrayList<>();

    @OneToMany(mappedBy = "estacaoTrabalho")
    private List<LicencaEntity> licencas = new ArrayList<>();
}
