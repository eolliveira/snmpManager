package com.example.snmpManager.services.ImpressoraService;

import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraInsertDTO;
import com.example.snmpManager.entities.ImpressoraEntity;
import com.example.snmpManager.repositories.ImpressoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindPrinterService {

    @Autowired
    private ImpressoraRepository impressoraRepository;

    public List<ImpressoraInsertDTO> findAll() {
        List<ImpressoraEntity> impressoras = impressoraRepository.findAll();
        return impressoras.stream().map(ImpressoraInsertDTO::new).collect(Collectors.toList());
    }

}
