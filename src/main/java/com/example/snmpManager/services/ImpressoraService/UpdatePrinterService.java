package com.example.snmpManager.services.ImpressoraService;

import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraUpdateDTO;
import com.example.snmpManager.entities.ImpressoraEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.ImpressoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdatePrinterService {
    private final ImpressoraRepository impressoraRepository;

    @Transactional
    public ImpressoraUpdateDTO updatePrinter(Long idAtivo, ImpressoraUpdateDTO dto) {
        Optional<ImpressoraEntity> opt = impressoraRepository.findById(idAtivo);
        ImpressoraEntity impressora = opt.orElseThrow(() -> new ResourceNotFoundException("Impressora id: " + idAtivo + " não encontrada."));

        impressora.setNome(dto.getNome());
        impressora.setNomeHost(dto.getNomeHost());
        impressora.setDescricao(dto.getDescricao());
        impressora.setInativo(dto.getInativo());
        impressora.setDtAquisicao(dto.getDtAquisicao());
        impressora.setDtVencimentoGarantia(dto.getDtVencimentoGarantia());
        impressora.setDtExpiracao(dto.getDtExpiracao());
        impressora.setValorCompra(dto.getValorCompra());
        impressora.setFornecedor(dto.getFornecedor());
        impressora.setObservacao(dto.getObservacao());
        impressora.setNumeroSerie(dto.getNumeroSerie());
        impressora.setModelo(dto.getModelo());

        return new ImpressoraUpdateDTO(impressora);
    }


}
