package com.example.snmpManager.services.DispositivoMovelService;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.DiscoParticaoDTO.DiscoParticaoDTO;
import com.example.snmpManager.dto.DispositivoMovelDTO.DispositivoMovelDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.InterfaceDTO.InterfaceDTO;
import com.example.snmpManager.entities.*;
import com.example.snmpManager.repositories.*;
import com.example.snmpManager.util.AddressValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class NewMobileDeviceService {

    private final AddressValidation addressValidation;
    private final EstacaoTrabalhoRepository estacaoTrabalhoRepository;

    private final DispositivoMovelRepository dispositivoMovelRepository;
    private final InterfaceRepository interfaceRepository;
    private final DiscoRepository discoRepository;
    private final DiscoParticaoRepository discoParticaoRepository;

    @Transactional
    public DispositivoMovelDTO insertNewMobileDevice(DispositivoMovelDTO dto) {
        addressValidation.addressAlreadyExists(dto);
        DispositivoMovelEntity device = new DispositivoMovelEntity(dto);
        device = dispositivoMovelRepository.save(device);
        return new DispositivoMovelDTO(device);
    }
}
