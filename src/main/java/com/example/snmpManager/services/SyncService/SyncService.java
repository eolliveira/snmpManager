package com.example.snmpManager.services.SyncService;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.DiscoParticaoDTO.DiscoParticaoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoSynchronizeDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceAtivoDTO;
import com.example.snmpManager.entities.DiscoEntity;
import com.example.snmpManager.entities.DiscoParticaoEntity;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
            InterfaceEntity entity = interfaceRepository.findByEnderecoIp(trapObject.getIpAddress());
            System.out.println(entity.getNomeLocal());
            System.out.println(entity.getEnderecoIp());
            System.out.println(entity.getEnderecoMac());
            System.out.println("id estação " + (entity.getEstacaoTrabalho().getId()));


            userSynchronizeWorkstationService.synchronizeWorstation(entity.getEstacaoTrabalho().getId());
            //agentSynchronizeWorkstationService.synchronizeWorstation(trapObject.getIpAddress());



        }
    }


}
