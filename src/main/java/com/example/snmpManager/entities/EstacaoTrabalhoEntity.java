package com.example.snmpManager.entities;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoDTO;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@DiscriminatorValue(value = "ESTACAOTRABALHO")
public class EstacaoTrabalhoEntity extends AtivoEntity implements Serializable {
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
    private List<InterfaceEntity> interfaces = new ArrayList<>();

    @OneToMany(mappedBy = "estacaoTrabalho")
    private List<DiscoEntity> discos = new ArrayList<>();

    public EstacaoTrabalhoEntity() {}

    public EstacaoTrabalhoEntity(EstacaoTrabalhoDTO dto) {
        this.setInativo(false);
        this.setStatus(StatusAtivo.DISPONIVEL);
        this.setFabricante(dto.getFabricante());
        this.setNumeroSerie(dto.getNumeroSerie());
        this.setModelo(dto.getModelo());
        this.sistemaOperacional = dto.getSistemaOperacional();
        this.processador = dto.getProcessador();
        this.arquiteturaSo = dto.getArquiteturaSo();
        this.memoriaRam = dto.getMemoriaRam();
        this.nomeHost = dto.getNomeHost();
        this.ultimoUsuarioLogado = dto.getUltimoUsuarioLogado();
        this.dominio = dto.getDominio();
        this.dnsList = dto.getDnsList();
        this.gateway = dto.getGateway();
    }
}
