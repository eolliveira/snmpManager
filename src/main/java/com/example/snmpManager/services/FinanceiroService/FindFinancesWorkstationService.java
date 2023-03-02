package com.example.snmpManager.services.FinanceiroService;

import com.example.snmpManager.dto.FinanceiroDTO.FinanceiroDTO;
import com.example.snmpManager.entities.FinanceiroEntity;
import com.example.snmpManager.repositories.FinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindFinancesWorkstationService {

    @Autowired
    FinanceiroRepository financeiroRepository;

    public List<FinanceiroDTO> findAllFinaces(Long idActive) {
        List<FinanceiroEntity> financas = financeiroRepository.findAllByAtivo_Id(idActive);
        return financas.stream().map(FinanceiroDTO::new).collect(Collectors.toList());
    }
}
