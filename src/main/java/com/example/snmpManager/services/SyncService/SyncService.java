package com.example.snmpManager.services.SyncService;

import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.objects.TrapObject;
import com.example.snmpManager.repositories.InterfaceRepository;
import com.example.snmpManager.services.EstacaoTrabalhoService.EstacaoTrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SyncService {

    @Autowired
    private InterfaceRepository interfaceRepository;

    @Autowired
    EstacaoTrabalhoService estacaoTrabalhoService;

    public void checkAgentSync(TrapObject trapObject) {
        if (Objects.equals(trapObject.getTipoAtivo(), "WORKSTATION")) {
            InterfaceEntity interfaceEntity = interfaceRepository.findByEnderecoIp(trapObject.getIpAddress());
            if (interfaceEntity != null) {
                estacaoTrabalhoService.synchronizeWorstation(interfaceEntity.getEstacaoTrabalho().getId());
            }
        }

    }


}
