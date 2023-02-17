package com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WindowsBasicDTO extends AtivoDTO {
    private String sistemaOperacional;
    private String processador;
    private String arquiteturaSo;
    private String memoriaRam;
    private String nomeHost;
    private String ultimoUsuarioLogado;
    private String dominio;
    private String dnsList;
    private String gateway;

    public WindowsBasicDTO(){
        super();
    }

    public WindowsBasicDTO(EstacaoTrabalhoEntity entity) {
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
        this.setObservacao(entity.getObeservacao());
        this.sistemaOperacional = entity.getSistemaOperacional();
        this.processador = entity.getProcessador();
        this.arquiteturaSo = entity.getArquiteturaSo();
        this.memoriaRam = entity.getMemoriaRam();
        this.nomeHost = entity.getNomeHost();
        this.ultimoUsuarioLogado = entity.getUltimoUsuarioLogado();
        this.dominio = entity.getDominio();
        this.dnsList = entity.getDnsList();
        this.gateway = entity.getGateway();
    }


}
