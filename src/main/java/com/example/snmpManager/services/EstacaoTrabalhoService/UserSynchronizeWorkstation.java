package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoSynchronizeDTO;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class UserSynchronizeWorkstation {

    @Autowired
    private EstacaoTrabalhoRepository estacaoTrabalhoRepository;

    @Autowired
    private EstacaoTrabalhoService estacaoTrabalhoService;

    @Autowired
    private UpdateWorkstationService updateWorkstationService;

    @Autowired
    private GetDataFromWorkstationService getDataFromWorkstationService;

    @Transactional
    public void synchronizeWorstation(Long idAtivo) {

        Optional<EstacaoTrabalhoEntity> opt = estacaoTrabalhoRepository.findById(idAtivo);
        EstacaoTrabalhoEntity estacaoTrabalho = opt.orElseThrow(() -> new ResourceNotFoundException("Estação id: " + idAtivo + " não encontrada."));

        WorkstationObject objAgent = new WorkstationObject();

        for (InterfaceEntity i : estacaoTrabalho.getInterfaces()) {
            if (i.getEnderecoIp() != "" && i.getEnderecoIp() != null) {
                WorkstationObject obj = getDataFromWorkstationService.getWorkstationData(i.getEnderecoIp());
                if (obj.getFabricante() != null) {
                    objAgent = obj;
                }
            }
        }

        if (objAgent.getFabricante() == null) {
            throw new ResourceNotFoundException("Nenhum endereco IPV4 definido para estação Id: " + idAtivo);
        }

        EstacaoTrabalhoSynchronizeDTO dto = new EstacaoTrabalhoSynchronizeDTO(objAgent);
        updateWorkstationService.updateWorkStation(idAtivo, dto);
    }


}
