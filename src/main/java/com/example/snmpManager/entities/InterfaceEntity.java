package com.example.snmpManager.entities;

import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Table(name = "ATIVOINTERFACE")
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class InterfaceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATIVOINTERFACE")
    private Long id;
    private String nomeLocal;
    private String fabricante;
    private String enderecoMac;
    @Column(unique = true)
    private String enderecoIp;
    private String mascaraSubRede;
    @ManyToOne
    @JoinColumn(name = "ID_ESTACAO_TRABALHO")
    private EstacaoTrabalhoEntity estacaoTrabalho;

    public InterfaceEntity() {
    }

    public InterfaceEntity(InterfaceDTO dto) {
        this.nomeLocal = dto.getNomeLocal();
        this.fabricante = dto.getFabricante();
        this.enderecoMac = dto.getEnderecoMac();
        this.enderecoIp = dto.getEnderecoIp();
        this.mascaraSubRede = dto.getMascaraSubRede();

    }

    public InterfaceEntity(InterfaceDTO dto, EstacaoTrabalhoEntity estacaoTrabalho) {
        this.nomeLocal = dto.getNomeLocal();
        this.fabricante = dto.getFabricante();
        this.enderecoMac = dto.getEnderecoMac();
        this.enderecoIp = dto.getEnderecoIp();
        this.mascaraSubRede = dto.getMascaraSubRede();
        this.estacaoTrabalho = estacaoTrabalho;
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
