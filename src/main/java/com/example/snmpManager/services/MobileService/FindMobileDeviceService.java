package com.example.snmpManager.services.MobileService;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoBasicDTO;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindMobileDeviceService {

    @Autowired
    private EstacaoTrabalhoRepository estacaoTrabalhoRepository;

    public List<EstacaoTrabalhoBasicDTO> findAll() {
        List<EstacaoTrabalhoEntity> estacoes = estacaoTrabalhoRepository.findAll();
        return estacoes.stream().map(EstacaoTrabalhoBasicDTO::new).collect(Collectors.toList());
    }

}
