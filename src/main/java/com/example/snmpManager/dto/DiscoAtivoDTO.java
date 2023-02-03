package com.example.snmpManager.dto;

import com.example.snmpManager.entities.DiscoAtivoEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DiscoAtivoDTO implements Serializable {

    private Long id;
    private String nome;
    private String modelo;
    private String numeroSerie;
    private String capacidade;
    private String usado;
    private String disponivel;

    private EstacaoTrabalhoDTO estacaoTrabalho;

    private List<DiscoAtivoParticaoDTO> particoes = new ArrayList<>();

    public DiscoAtivoDTO(DiscoAtivoEntity entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.modelo = entity.getModelo();
        this.numeroSerie = entity.getNumeroSerie();
        this.capacidade = entity.getCapacidade();
        this.usado = entity.getUsado();
        this.disponivel = entity.getDisponivel();
        this.estacaoTrabalho = new EstacaoTrabalhoDTO(entity.getEstacaoTrabalho());

        entity.getParticoes().stream().map(particao -> this.particoes.add(new DiscoAtivoParticaoDTO(particao))).collect(Collectors.toList());
    }
}
