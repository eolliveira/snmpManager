package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.repositories.InterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class AgentSynchronizeWorkstation {

    @Autowired
    private InterfaceRepository interfaceRepository;

    @Transactional
    public void synchronizeWorstation(String ipAdrress) {

        //tratar exception
        InterfaceEntity interfaceAtivo = interfaceRepository.findByEnderecoIp(ipAdrress);

        //interfaceAtivo.getEstacaoTrabalho().getId();

    }


}
