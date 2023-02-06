package com.example.snmpManager.dto;

import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.objects.WindowsObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class EstacaoTrabalhoUpdateDTO {
    private String fabricante;
    private String numeroSerie;
    private String modelo;
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
    private List<AtivoDiscoDTO> discos = new ArrayList<>();

    public EstacaoTrabalhoUpdateDTO(){
        super();
    }

    public EstacaoTrabalhoUpdateDTO(EstacaoTrabalhoEntity entity) {
        this.fabricante = entity.getFabricante();
        this.numeroSerie = entity.getNumeroSerie();
        this.modelo = entity.getModelo();
        this.sistemaOperacional = entity.getSistemaOperacional();
        this.processador = entity.getProcessador();
        this.arquiteturaSo = entity.getArquiteturaSo();
        this.memoriaRam = entity.getMemoriaRam();
        this.nomeHost = entity.getNomeHost();
        this.ultimoUsuarioLogado = entity.getUltimoUsuarioLogado();
        this.dominio = entity.getDominio();
        this.dnsList = entity.getDnsList();
        this.gateway = entity.getGateway();

//        entity.getInterfaces().stream().map(i -> this.interfaces.add(new InterfaceAtivoDTO(i))).collect(Collectors.toList());
//        entity.getDiscos().stream().map(disco -> this.discos.add(new DiscoAtivoDTO(disco))).collect(Collectors.toList());
    }

    public EstacaoTrabalhoUpdateDTO(WindowsObject obj) {
        this.fabricante = obj.getFabricante();
        this.numeroSerie = obj.getNumeroSerie();
        this.modelo = obj.getModelo();
        this.sistemaOperacional = obj.getSistemaOperacional();
        this.processador = obj.getProcessador();
        this.arquiteturaSo = obj.getArquiteturaSo();
        this.memoriaRam = obj.getMemoriaRam();
        this.nomeHost = obj.getNomeHost();
        this.ultimoUsuarioLogado = obj.getUltimoUsuarioLogado();
        this.dominio = obj.getDominio();
        this.dnsList = obj.getDnsList();
        this.gateway = obj.getGateway();

        obj.getInterfaces().stream().map(i -> this.interfaces.add(new InterfaceAtivoDTO(i))).collect(Collectors.toList());
        obj.getDiscos().stream().map(disco -> this.discos.add(new AtivoDiscoDTO(disco))).collect(Collectors.toList());
    }

}
