package com.example.snmpManager.dto.InterfaceDTO;

import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.InterfaceObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class InterfaceDTO implements Serializable {
    private Long id;
    private String nomeLocal;
    private String fabricante;
    private String enderecoMac;
    private String enderecoIp;
    private String mascaraSubRede;

    public InterfaceDTO(){}

    public InterfaceDTO(InterfaceEntity entity) {
        this.id = entity.getId();
        this.nomeLocal = entity.getNomeLocal();
        this.fabricante = entity.getFabricante();
        this.enderecoMac = entity.getEnderecoMac();
        this.enderecoIp = entity.getEnderecoIp();
        this.mascaraSubRede = entity.getMascaraSubRede();
    }

    public InterfaceDTO(InterfaceObject obj) {
        this.nomeLocal = obj.getNomeLocal();
        this.fabricante = obj.getFabricante();
        this.enderecoMac = obj.getEnderecoMac();
        this.enderecoIp = obj.getEnderecoIp();
        this.mascaraSubRede = obj.getMascaraSubRede();
    }

    public InterfaceDTO(InterfaceDTO i) {
        this.id = i.getId();
        this.nomeLocal = i.getNomeLocal();
        this.fabricante = i.getFabricante();
        this.enderecoMac = i.getEnderecoMac();
        this.enderecoIp = i.getEnderecoIp();
        this.mascaraSubRede = i.getMascaraSubRede();
    }
}
