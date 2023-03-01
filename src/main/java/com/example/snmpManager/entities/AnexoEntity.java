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

    //TODO(VERIFICAR SE CHAVE É NECESSÁRIA)
    @ManyToOne
    @JoinColumn(name = "ID_MOVIMENTO")
    private UsuarioEntity movimento;

    @ManyToOne
    @JoinColumn(name = "ID_FINANCEIRO")
    private FinanceiroEntity financeiro;
}
