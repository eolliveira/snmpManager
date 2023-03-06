package com.example.snmpManager.services.ImpressoraService;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.DiscoParticaoDTO.DiscoParticaoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraInsertDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceDTO;
import com.example.snmpManager.entities.*;
import com.example.snmpManager.exceptions.InvalidAddressExecption;
import com.example.snmpManager.mibs.ImpressoraMIB;
import com.example.snmpManager.objects.PrinterObject;
import com.example.snmpManager.repositories.ImpressoraRepository;
import com.example.snmpManager.services.SNMPRequestClient;
import com.example.snmpManager.util.AddressValidation;
import lombok.RequiredArgsConstructor;
import org.snmp4j.smi.OID;
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

        InterfaceEntity interfaceEntity = new InterfaceEntity();

        return null;
    }
}
