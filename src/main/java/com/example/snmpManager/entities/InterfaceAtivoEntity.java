package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "INTERFACEATIVO")
@Entity
@Data
public class InterfaceAtivoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeLocal;
    private String fabricante;
    private String enderecoMac;
    private String enderecoIp;
    private String mascaraSubRede;

    @ManyToOne
    @JoinColumn(name = "ID_ESTACAO_TRABALHO")
    private EstacaoTrabalhoEntity estacaoTrabalho;

}
