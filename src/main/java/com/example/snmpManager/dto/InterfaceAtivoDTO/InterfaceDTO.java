package com.example.snmpManager.dto.InterfaceAtivoDTO;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.InterfaceObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class InterfaceDTO implements Serializable {
    private Long id;
    private String nomeLocal;
    private String fabricante;
    private String enderecoMac;
    private String enderecoIp;
    private String mascaraSubRede;

    //private EstacaoTrabalhoDTO estacaoTrabalho;

    public InterfaceDTO(){}

    public InterfaceDTO(InterfaceEntity entity) {
        this.id = entity.getId();
        this.nomeLocal = entity.getNomeLocal();
        this.fabricante = entity.getFabricante();
        this.enderecoMac = entity.getEnderecoMac();
        this.enderecoIp = entity.getEnderecoIp();
        this.mascaraSubRede = entity.getMascaraSubRede();
        //this.estacaoTrabalho = new EstacaoTrabalhoDTO(entity.getEstacaoTrabalho());
    }

    public InterfaceDTO(InterfaceObject obj) {
        this.nomeLocal = obj.getNomeLocal();
        this.fabricante = obj.getFabricante();
        this.enderecoMac = obj.getEnderecoMac();
        this.enderecoIp = obj.getEnderecoIp();
        this.mascaraSubRede = obj.getMascaraSubRede();
    }
}