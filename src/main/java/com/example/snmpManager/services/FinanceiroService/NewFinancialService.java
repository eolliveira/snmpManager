package com.example.snmpManager.services.FinanceiroService;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.DiscoParticaoDTO.DiscoParticaoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.FinanceiroDTO.FinanceiroDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceDTO;
import com.example.snmpManager.entities.*;
import com.example.snmpManager.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class NewFinancialService {

    private final FinanceiroRepository financeiroRepository;

    @Transactional
    public FinanceiroDTO insertNewFinancial(FinanceiroDTO dto) {
        FinanceiroEntity financeiro = new FinanceiroEntity(dto);
        financeiroRepository.save(financeiro);
        return new FinanceiroDTO(financeiro);
    }
}
