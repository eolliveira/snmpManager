package com.example.snmpManager.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorValue(value = "IMPRESSORA")
public class ImpressoraEntity extends AtivoEntity {
    private String tempoLigado;
    private String totalImpressoes;

    ///TODO(VERIFICAR MAPEAMENTO)
//    @OneToMany(mappedBy = "estacaoTrabalho", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<InterfaceEntity> interfaces = new ArrayList<>();

}
