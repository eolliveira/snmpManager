package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.repositories.DiscoParticaoRepository;
import com.example.snmpManager.repositories.DiscoRepository;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import com.example.snmpManager.repositories.InterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AgentSynchronizeWorkstationService {


    @Autowired
    private EstacaoTrabalhoRepository estacaoTrabalhoRepository;

    @Autowired
    private InterfaceRepository interfaceRepository;

    @Autowired
    private DiscoRepository discoRepository;

    @Autowired
    private GetDataFromWorkstationService getDataFromWorkstationService;

    @Autowired
    private DiscoParticaoRepository discoParticaoRepository;

    @Transactional
    public void synchronizeWorstation(String ipAdrress) {


    }


}
