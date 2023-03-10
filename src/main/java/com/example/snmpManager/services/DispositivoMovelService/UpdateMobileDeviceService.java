package com.example.snmpManager.services.DispositivoMovelService;

import com.example.snmpManager.dto.DispositivoMovelDTO.DispositivoMovelDTO;
import com.example.snmpManager.entities.DispositivoMovelEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.DispositivoMovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateMobileDeviceService {
    private final DispositivoMovelRepository dispositivoMovelRepository;

    @Transactional
    public DispositivoMovelDTO updateMobileDevice(Long idAtivo, DispositivoMovelDTO dto) {
        Optional<DispositivoMovelEntity> opt = dispositivoMovelRepository.findById(idAtivo);
        DispositivoMovelEntity device = opt.orElseThrow(() -> new ResourceNotFoundException("Dispositivo id: " + idAtivo + " não encontrado."));

        device.setNome(dto.getNome());
        device.setDescricao(dto.getDescricao());
        device.setInativo(dto.getInativo());
        device.setStatus(dto.getStatus());
        device.setDtAquisicao(dto.getDtAquisicao());
        device.setDtVencimentoGarantia(dto.getDtVencimentoGarantia());
        device.setDtExpiracao(dto.getDtExpiracao());
        device.setValorCompra(dto.getValorCompra());
        device.setFornecedor(dto.getFornecedor());
        device.setObservacao(dto.getObservacao());
        device.setFabricante(dto.getFabricante());
        device.setNumeroSerie(dto.getNumeroSerie());
        device.setModelo(dto.getModelo());
        device.setGateway(dto.getGateway());
        device.setGateway(dto.getGateway());
        device.setNomeHost(dto.getNomeHost());
        device.setDominio(dto.getDominio());
        device.setVersaoAndroid(dto.getVersaoAndroid());
        dispositivoMovelRepository.save(device);

        return new DispositivoMovelDTO(device);
    }
}
