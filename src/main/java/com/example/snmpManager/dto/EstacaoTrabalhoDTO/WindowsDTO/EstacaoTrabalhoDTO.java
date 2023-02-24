package com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceAtivoDTO;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
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
    private List<DiscoDTO> discos = new ArrayList<>();

    public EstacaoTrabalhoDTO(){
        super();
    }

    public EstacaoTrabalhoDTO(EstacaoTrabalhoEntity entity) {
        this.setId(entity.getId());
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
        this.setObservacao(entity.getObservacao());
        this.sistemaOperacional = entity.getSistemaOperacional();
        this.processador = entity.getProcessador();
        this.arquiteturaSo = entity.getArquiteturaSo();
        this.memoriaRam = entity.getMemoriaRam();
        this.nomeHost = entity.getNomeHost();
        this.ultimoUsuarioLogado = entity.getUltimoUsuarioLogado();
        this.dominio = entity.getDominio();
        this.dnsList = entity.getDnsList();
        this.gateway = entity.getGateway();

        //entity.getInterfaces().stream().map(i -> this.interfaces.add(new InterfaceAtivoDTO(i))).collect(Collectors.toList());
        //entity.getDiscos().stream().map(disco -> this.discos.add(new DiscoDTO(disco))).collect(Collectors.toList());
    }


}
