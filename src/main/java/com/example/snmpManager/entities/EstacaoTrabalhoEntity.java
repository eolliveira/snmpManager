package com.example.snmpManager.entities;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.*;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "estacaoTrabalho", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InterfaceEntity> interfaces = new ArrayList<>();

    @OneToMany(mappedBy = "estacaoTrabalho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiscoEntity> discos = new ArrayList<>();

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
        this.setObeservacao(dto.getObservacao());
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
