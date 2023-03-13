package com.example.snmpManager.entities;

import com.example.snmpManager.dto.UsuarioDTO.UsuarioDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USUARIO")
@Data
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "usuario")
    private List<AtivoEntity> ativos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<MovimentoEntity> movimentos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<CustoEntity> custos = new ArrayList<>();

    public UsuarioEntity() {
    }

    public UsuarioEntity(UsuarioDTO dto) {
        this.id = dto.getId();
    }

}
