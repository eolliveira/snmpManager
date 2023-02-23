package com.example.snmpManager.services.SyncService;

import com.example.snmpManager.objects.TrapObject;
import com.example.snmpManager.repositories.DiscoParticaoRepository;
import com.example.snmpManager.repositories.DiscoRepository;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import com.example.snmpManager.repositories.InterfaceRepository;
import com.example.snmpManager.services.EstacaoTrabalhoService.AgentSynchronizeWorkstationService;
import com.example.snmpManager.services.EstacaoTrabalhoService.GetDataFromWorkstationService;
import com.example.snmpManager.services.EstacaoTrabalhoService.UserSynchronizeWorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SyncService {

    @Autowired
    AgentSynchronizeWorkstationService agentSynchronizeWorkstationService;

    @Autowired
    InterfaceRepository interfaceRepository;

    @Autowired
    DiscoRepository discoRepository;

    @Autowired
    DiscoParticaoRepository discoParticaoRepository;

    @Autowired
    EstacaoTrabalhoRepository estacaoTrabalhoRepository;

    @Autowired
    GetDataFromWorkstationService getDataFromWorkstationService;

    @Autowired
    UserSynchronizeWorkstationService userSynchronizeWorkstationService;


    public void checkAgentSync(TrapObject trapObject) {
        if (Objects.equals(trapObject.getTipoAtivo(), "WORKSTATION")) {
           agentSynchronizeWorkstationService.synchronizeWorstation(trapObject.getIpAddress());
        }
    }


}
