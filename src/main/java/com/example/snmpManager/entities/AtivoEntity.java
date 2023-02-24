package com.example.snmpManager.entities;

import com.example.snmpManager.dto.AtivoDTO.AtivoDTO;
import com.example.snmpManager.entities.enums.StatusAtivo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Table(name = "ATIVO")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TP_ATIVO")
@Getter
@Setter
@NoArgsConstructor
public abstract class AtivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATIVO")
    private Long id;
    private String nome;
    private String fabricante;
    private String modelo;
    private String descricao;
    private String numeroSerie;
    private Boolean inativo;
    @Enumerated(EnumType.STRING)
    private StatusAtivo status;
    private Date dtAquisicao;
    private Date dtVencimentoGarantia;
    private Date dtExpiracao;
    private Date ultimoSincronismo;
    private BigDecimal valorCompra;
    private String fornecedor;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "ativo")
    private List<MovimentoEntity> movimentos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "ATIVOLICENCA",
            joinColumns = @JoinColumn(name = "ID_ATIVO"),
            inverseJoinColumns = @JoinColumn(name = "ID_LICENCA"))
    private Set<LicencaEntity> licencas = new HashSet<>();

    public AtivoEntity(AtivoDTO dto) {
        this.id = dto.getId();
    }
}
