package com.example.snmpManager.services.DispositivoMovelService;

import com.example.snmpManager.dto.DispositivoMovelDTO.DispositivoMovelDTO;
import com.example.snmpManager.entities.DispositivoMovelEntity;
import com.example.snmpManager.repositories.DispositivoMovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindMobileDeviceService {

    @Autowired
    private DispositivoMovelRepository dispositivoMovelRepository;

    public List<DispositivoMovelDTO> findAllMobileDevices() {
        List<DispositivoMovelEntity> dispositivos = dispositivoMovelRepository.findAll();
        return dispositivos.stream().map(DispositivoMovelDTO::new).collect(Collectors.toList());
    }

}
