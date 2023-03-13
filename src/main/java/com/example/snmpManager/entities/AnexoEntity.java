package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ANEXO")
@Data
public class AnexoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_ATIVO")
    private AtivoEntity ativo;

    @ManyToOne
    @JoinColumn(name = "ID_CUSTO")
    private CustoEntity custo;
}
