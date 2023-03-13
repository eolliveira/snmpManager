package com.example.snmpManager.dto.DiscoDTO;

import com.example.snmpManager.dto.DiscoParticaoDTO.DiscoParticaoDTO;
import com.example.snmpManager.entities.DiscoEntity;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.HardDiskObject;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DiscoDTO implements Serializable {

    private Long id;
    private String nome;
    private String modelo;
    private String numeroSerie;
    private String capacidade;

    private List<DiscoParticaoDTO> particoes = new ArrayList<>();

    public DiscoDTO() {}

    public DiscoDTO(DiscoEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.modelo = entity.getModelo();
        this.numeroSerie = entity.getNumeroSerie();
        this.capacidade = entity.getCapacidade();
        entity.getParticoes().stream().map(particao -> this.particoes.add(new DiscoParticaoDTO(particao))).collect(Collectors.toList());
    }

    public DiscoDTO(HardDiskObject disc) {
        this.nome = disc.getNome();
        this.modelo = disc.getModelo();
        this.numeroSerie = disc.getNumeroSerie();
        this.capacidade = disc.getCapacidade();
        disc.getParticoes().stream().map(particao -> this.particoes.add(new DiscoParticaoDTO(particao))).collect(Collectors.toList());
    }
}
