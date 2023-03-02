package com.example.snmpManager.entities;

import com.example.snmpManager.dto.FinanceiroDTO.FinanceiroDTO;
import com.example.snmpManager.dto.FinanceiroDTO.FinanceiroInsertDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Table(name = "ATIVOFINANCEIRO")
@Entity
@Data
public class FinanceiroEntity {

    //TODO(id do usuário e id do ativo devem ser obrigatórios ao inserir)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FINANCEIRO")
    private Long id;
    private String descricao;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dtFinanceiro;

    private Double valor;
    @ManyToOne()
    @JoinColumn(name = "ID_ATIVO")
    private AtivoEntity ativo;
    @ManyToOne()
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntity usuario;
    //TODO(Usuario deve ser o mesmo que estiver logado)
    @OneToMany(mappedBy = "financeiro")
    private List<AnexoEntity> anexos;

    public FinanceiroEntity() {
    }

    public FinanceiroEntity(AtivoEntity ativo, UsuarioEntity usuario, FinanceiroInsertDTO dto) {
        this.id = dto.getId();
        this.descricao = dto.getDescricao();
        this.valor = dto.getValor();
        this.dtFinanceiro = Instant.now();
        this.ativo = ativo;
        this.usuario = usuario;
    }
}