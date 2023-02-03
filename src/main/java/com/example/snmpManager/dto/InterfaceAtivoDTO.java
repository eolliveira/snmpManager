package com.example.snmpManager.dto;

import com.example.snmpManager.entities.InterfaceAtivoEntity;
import lombok.Data;

@Data
public class InterfaceAtivoDTO {
    private Long id;
    private String nomeLoccal;
    private String fabricante;
    private String enderecoMac;
    private String enderecoIp;
    private String mascaraSubRede;

    private EstacaoTrabalhoDTO estacaoTrabalho;

    public InterfaceAtivoDTO(InterfaceAtivoEntity entity) {
        this.id = entity.getId();
        this.nomeLoccal = entity.getNomeLoccal();
        this.fabricante = entity.getFabricante();
        this.enderecoMac = entity.getEnderecoMac();
        this.enderecoIp = entity.getEnderecoIp();
        this.mascaraSubRede = entity.getMascaraSubRede();
        this.estacaoTrabalho = new EstacaoTrabalhoDTO(entity.getEstacaoTrabalho());
    }
}
