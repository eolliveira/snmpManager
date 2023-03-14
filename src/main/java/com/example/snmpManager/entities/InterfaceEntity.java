package com.example.snmpManager.entities;

import com.example.snmpManager.dto.InterfaceDTO.InterfaceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "INTERFACE")
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class InterfaceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INTERFACE")
    private Long id;
    private String nomeLocal;
    private String fabricante;
    private String enderecoMac;
    private String enderecoIp;
    private String mascaraSubRede;
    @ManyToOne
    @JoinColumn(name = "ID_ATIVO")
    private AtivoEntity ativo;

    public InterfaceEntity() {
    }

    public InterfaceEntity(InterfaceDTO dto) {
        this.nomeLocal = dto.getNomeLocal();
        this.fabricante = dto.getFabricante();
        this.enderecoMac = dto.getEnderecoMac();
        this.enderecoIp = dto.getEnderecoIp();
        this.mascaraSubRede = dto.getMascaraSubRede();
    }

    public InterfaceEntity(InterfaceDTO dto, AtivoEntity ativo) {
        this.nomeLocal = dto.getNomeLocal();
        this.fabricante = dto.getFabricante();
        this.enderecoMac = dto.getEnderecoMac();
        this.enderecoIp = dto.getEnderecoIp();
        this.mascaraSubRede = dto.getMascaraSubRede();
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterfaceEntity)) return false;
        InterfaceEntity that = (InterfaceEntity) o;
        return Objects.equals(fabricante, that.fabricante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fabricante);
    }

}
