package com.example.snmpManager.services.SyncService;

import com.example.snmpManager.objects.TrapObject;
import com.example.snmpManager.services.EstacaoTrabalhoService.AgentSynchronizeWorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SyncService {

    @Autowired
    AgentSynchronizeWorkstationService agentSynchronizeWorkstationService;
    public void checkAgentSync(TrapObject trapObject) {


    }


}
