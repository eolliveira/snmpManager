package com.example.snmpManager.dto.InterfaceAtivoDTO;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.WindowsDTO;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WindowsObjects.InterfaceObject;
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

    private WindowsDTO estacaoTrabalho;

    public InterfaceAtivoDTO(){}

    public InterfaceAtivoDTO(InterfaceEntity entity) {
        this.id = entity.getId();
        this.nomeLocal = entity.getNomeLocal();
        this.fabricante = entity.getFabricante();
        this.enderecoMac = entity.getEnderecoMac();
        this.enderecoIp = entity.getEnderecoIp();
        this.mascaraSubRede = entity.getMascaraSubRede();
        this.estacaoTrabalho = new WindowsDTO(entity.getEstacaoTrabalho());
    }

    public InterfaceAtivoDTO(InterfaceObject obj) {
        this.nomeLocal = obj.getNomeLocal();
        this.fabricante = obj.getFabricante();
        this.enderecoMac = obj.getEnderecoMac();
        this.enderecoIp = obj.getEnderecoIp();
        this.mascaraSubRede = obj.getMascaraSubRede();
    }
}
