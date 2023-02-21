package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoBasicDTO;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindWorkstationService {

    @Autowired
    private EstacaoTrabalhoRepository estacaoTrabalhoRepository;

    public List<EstacaoTrabalhoBasicDTO> findAll() {
        List<EstacaoTrabalhoEntity> estacoes = estacaoTrabalhoRepository.findAll();
        return estacoes.stream().map(EstacaoTrabalhoBasicDTO::new).collect(Collectors.toList());
    }

}
