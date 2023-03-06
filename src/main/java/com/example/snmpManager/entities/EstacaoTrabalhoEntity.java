package com.example.snmpManager.entities;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String tempoLigado;
    private String nomeHost;
    private String ultimoUsuarioLogado;
    private String dominio;
    private String dnsList;
    private String gateway;
    @OneToMany(mappedBy = "estacaoTrabalho", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DiscoEntity> discos = new HashSet<>();

    public EstacaoTrabalhoEntity() {}

    public EstacaoTrabalhoEntity(EstacaoTrabalhoDTO dto) {
        this.setNome(dto.getNome());
        this.setDescricao(dto.getDescricao());
        this.setInativo(false);
        this.setStatus(StatusAtivo.DISPONIVEL);
        this.setFabricante(dto.getFabricante());
        this.setNumeroSerie(dto.getNumeroSerie());
        this.setModelo(dto.getModelo());
        this.setDtAquisicao(dto.getDtAquisicao());
        this.setDtVencimentoGarantia(dto.getDtVencimentoGarantia());
        this.setDtExpiracao(dto.getDtExpiracao());
        this.setUltimoSincronismo(dto.getUltimoSincronismo());
        this.setValorCompra(dto.getValorCompra());
        this.setFornecedor(dto.getFornecedor());
        this.setObservacao(dto.getObservacao());
        this.sistemaOperacional = dto.getSistemaOperacional();
        this.processador = dto.getProcessador();
        this.arquiteturaSo = dto.getArquiteturaSo();
        this.memoriaRam = dto.getMemoriaRam();
        this.tempoLigado = dto.getTempoLigado();
        this.nomeHost = dto.getNomeHost();
        this.ultimoUsuarioLogado = dto.getUltimoUsuarioLogado();
        this.dominio = dto.getDominio();
        this.dnsList = dto.getDnsList();
        this.gateway = dto.getGateway();
    }
}
