package com.example.snmpManager.services.CustoService;

import com.example.snmpManager.dto.CustoDTO.CustoDTO;
import com.example.snmpManager.entities.CustoEntity;
import com.example.snmpManager.repositories.CustoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindTheAssetCostsService {

    @Autowired
    CustoRepository custoRepository;

    public List<CustoDTO> findAllCosts(Long idActive) {
        List<CustoEntity> custos = custoRepository.findAllByAtivo_Id(idActive);
        return custos.stream().map(CustoDTO::new).collect(Collectors.toList());
    }
}
