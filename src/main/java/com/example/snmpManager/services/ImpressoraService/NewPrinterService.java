package com.example.snmpManager.services.ImpressoraService;

import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraInsertDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceDTO;
import com.example.snmpManager.entities.ImpressoraEntity;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.repositories.ImpressoraRepository;
import com.example.snmpManager.util.AddressValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NewPrinterService {

    private final ImpressoraRepository impressoraRepository;

    private final AddressValidation addressValidation;

    @Transactional
    public ImpressoraInsertDTO insertNewPrinter(ImpressoraInsertDTO dto) {

        List<InterfaceDTO> interfacesDto = new ArrayList<>();
        interfacesDto.add(new InterfaceDTO(null, null, null, dto.getEnderecoMac(), dto.getEnderecoIp(), dto.getMascaraSubRede()));
        interfacesDto.stream().map(i -> dto.getInterfaces().add(new InterfaceDTO(i))).collect(Collectors.toList());

        addressValidation.addressAlreadyExists(dto);

        ImpressoraEntity impressora = new ImpressoraEntity(dto);
        impressora = impressoraRepository.save(impressora);

        impressora.getInterfaces().clear();
        impressora.getInterfaces().add(new InterfaceEntity(null, null, null,
                dto.getEnderecoMac(),
                dto.getEnderecoIp(),
                dto.getMascaraSubRede(),
                impressora));

        return new ImpressoraInsertDTO(impressora);
    }
}
