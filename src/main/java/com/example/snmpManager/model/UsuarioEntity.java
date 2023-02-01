package com.example.snmpManager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USUARIO")
@Data
public class UsuarioEntity {
    //tabela de ususário ja existente

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "usuario")
    private List<AtivoEntity> ativos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<MovimentoAtivoEntity> movimentosAtivo = new ArrayList<>();
}
