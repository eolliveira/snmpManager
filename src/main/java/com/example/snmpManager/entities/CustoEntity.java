package com.example.snmpManager.entities;

import com.example.snmpManager.dto.CustoDTO.CustoInsertDTO;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Table(name = "CUSTO")
@Entity
@Data
public class CustoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUSTO")
    private Long id;
    private String descricao;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dtCusto;
    private BigDecimal valor;

    @ManyToOne()
    @JoinColumn(name = "ID_ATIVO")
    private AtivoEntity ativo;
    @ManyToOne()
    @JoinColumn(name = "ID_USUARIO")
    //TODO(Usuario deve ser o mesmo que estiver logado)
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "custo")
    private List<AnexoEntity> anexos;

    public CustoEntity() {
    }

    public CustoEntity(AtivoEntity ativo, UsuarioEntity usuario, CustoInsertDTO dto) {
        this.id = dto.getId();
        this.descricao = dto.getDescricao();
        this.valor = dto.getValor();
        this.dtCusto = Instant.now();
        this.ativo = ativo;
        this.usuario = usuario;
    }
}
