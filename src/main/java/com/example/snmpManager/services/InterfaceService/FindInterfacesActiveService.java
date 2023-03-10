package com.example.snmpManager.services.InterfaceService;

import com.example.snmpManager.dto.InterfaceDTO.InterfaceDTO;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.repositories.InterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindInterfacesActiveService {

    @Autowired
    InterfaceRepository interfaceRepository;

    public List<InterfaceDTO> findAllInterfaces(Long idActive) {
        List<InterfaceEntity> interfaces = interfaceRepository.findAllByAtivo_Id(idActive);
        return interfaces.stream().map(InterfaceDTO::new).collect(Collectors.toList());
    }
}
