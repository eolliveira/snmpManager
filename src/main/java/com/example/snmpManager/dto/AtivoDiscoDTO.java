package com.example.snmpManager.dto;

import com.example.snmpManager.entities.DiscoEntity;
import com.example.snmpManager.objects.HardDiskObject;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AtivoDiscoDTO implements Serializable {

    private Long id;
    private String nome;
    private String modelo;
    private String numeroSerie;
    private String capacidade;
    private String usado;
    private String disponivel;

    private EstacaoTrabalhoDTO estacaoTrabalho;

    private List<AtivoDiscoParticaoDTO> particoes = new ArrayList<>();

    public AtivoDiscoDTO() {}

    public AtivoDiscoDTO(DiscoEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.modelo = entity.getModelo();
        this.numeroSerie = entity.getNumeroSerie();
        this.capacidade = entity.getCapacidade();
        this.usado = entity.getUsado();
        this.disponivel = entity.getDisponivel();
        this.estacaoTrabalho = new EstacaoTrabalhoDTO(entity.getEstacaoTrabalho());
        entity.getParticoes().stream().map(particao -> this.particoes.add(new AtivoDiscoParticaoDTO(particao))).collect(Collectors.toList());
    }

    public AtivoDiscoDTO(HardDiskObject disc) {
        this.nome = disc.getNome();
        this.modelo = disc.getModelo();
        this.numeroSerie = disc.getNumeroSerie();
        this.capacidade = disc.getCapacidade();
        this.usado = disc.getUsado();
        this.disponivel = disc.getDisponivel();
        disc.getParticoes().stream().map(particao -> this.particoes.add(new AtivoDiscoParticaoDTO(particao))).collect(Collectors.toList());
    }
}
