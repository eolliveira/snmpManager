package com.example.snmpManager.dto;

import com.example.snmpManager.entities.InterfaceAtivoEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class InterfaceAtivoDTO implements Serializable {
    private Long id;
    private String nomeLocal;
    private String fabricante;
    private String enderecoMac;
    private String enderecoIp;
    private String mascaraSubRede;

    private EstacaoTrabalhoDTO estacaoTrabalho;

    public InterfaceAtivoDTO(){}

    public InterfaceAtivoDTO(InterfaceAtivoEntity entity) {
        this.id = entity.getId();
        this.nomeLocal = entity.getNomeLocal();
        this.fabricante = entity.getFabricante();
        this.enderecoMac = entity.getEnderecoMac();
        this.enderecoIp = entity.getEnderecoIp();
        this.mascaraSubRede = entity.getMascaraSubRede();
        this.estacaoTrabalho = new EstacaoTrabalhoDTO(entity.getEstacaoTrabalho());
    }
}