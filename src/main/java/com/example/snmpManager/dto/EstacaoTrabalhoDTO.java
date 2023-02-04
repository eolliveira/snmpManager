package com.example.snmpManager.dto;

import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class EstacaoTrabalhoDTO extends AtivoDTO {
    private String sistemaOperacional;
    private String processador;
    private String arquiteturaSo;
    private String memoriaRam;
    private String nomeHost;
    private String ultimoUsuarioLogado;
    private String dominio;
    private String dnsList;
    private String gateway;

    private List<InterfaceAtivoDTO> interfaces = new ArrayList<>();
    private List<DiscoAtivoDTO> discos = new ArrayList<>();

    public EstacaoTrabalhoDTO(){
        super();
    }

    public EstacaoTrabalhoDTO(EstacaoTrabalhoEntity entity) {
        super(entity);
        this.sistemaOperacional = entity.getSistemaOperacional();
        this.processador = entity.getProcessador();
        this.arquiteturaSo = entity.getArquiteturaSo();
        this.memoriaRam = entity.getMemoriaRam();
        this.nomeHost = entity.getNomeHost();
        this.ultimoUsuarioLogado = entity.getUltimoUsuarioLogado();
        this.dominio = entity.getDominio();
        this.dnsList = entity.getDnsList();
        this.gateway = entity.getGateway();

        entity.getInterfaces().stream().map(i -> this.interfaces.add(new InterfaceAtivoDTO(i))).collect(Collectors.toList());
        entity.getDiscos().stream().map(disco -> this.discos.add(new DiscoAtivoDTO(disco))).collect(Collectors.toList());
    }


}
