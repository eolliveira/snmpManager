package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ANEXO")
@Data
public class AnexoEntity {
    //tabela de ususário ja existente

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_MOVIMENTO")
    private UsuarioEntity movimento;
}
