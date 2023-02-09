package com.example.snmpManager.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
    private String enderecoIp;
    private String mascaraSubRede;

    @ManyToOne
    @JoinColumn(name = "ID_ESTACAO_TRABALHO")
    private EstacaoTrabalhoEntity estacaoTrabalho;

    public InterfaceEntity() {}
}
