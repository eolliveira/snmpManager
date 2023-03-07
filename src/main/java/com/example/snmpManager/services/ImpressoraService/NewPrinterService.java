package com.example.snmpManager.services.ImpressoraService;

import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraInsertDTO;
import com.example.snmpManager.entities.ImpressoraEntity;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.repositories.ImpressoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class NewPrinterService {

    private final ImpressoraRepository impressoraRepository;

    @Transactional
    public ImpressoraInsertDTO insertNewPrinter(ImpressoraInsertDTO dto) {

       //TODO(Verificar se impressora ja esta cadastrada)

        ImpressoraEntity impressora = new ImpressoraEntity(dto);
        impressora = impressoraRepository.save(impressora);

        impressora.getInterfaces().clear();
        impressora.getInterfaces().add(new InterfaceEntity(
                null,
                null,
                null,
                dto.getEnderecoMac(),
                dto.getEnderecoIp(),
                dto.getMascaraSubRede(),
                impressora));

        return new ImpressoraInsertDTO(impressora);
    }
}
