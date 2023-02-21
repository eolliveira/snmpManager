package com.example.snmpManager.services.SyncService;

import com.example.snmpManager.objects.TrapObject;
import com.example.snmpManager.services.EstacaoTrabalhoService.AgentSynchronizeWorkstation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SyncService {

    @Autowired
    AgentSynchronizeWorkstation agentSynchronizeWorkstation;
    public void checkAgentSync(TrapObject trapObject) {
        if (Objects.equals(trapObject.getTipoAtivo(), "WORKSTATION")) {
                agentSynchronizeWorkstation.synchronizeWorstation(trapObject.getIpAddress());
        }

    }


}
