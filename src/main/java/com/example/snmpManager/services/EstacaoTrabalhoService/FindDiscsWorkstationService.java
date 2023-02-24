package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceDTO;
import com.example.snmpManager.entities.DiscoEntity;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.repositories.DiscoRepository;
import com.example.snmpManager.repositories.InterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindDiscsWorkstationService {

    @Autowired
    DiscoRepository discoRepository;


    public List<DiscoDTO> findAllDiscs(Long idActive) {
        //TODO(Tratar exception)
        List<DiscoEntity> discos = discoRepository.findAllByEstacaoTrabalho_Id(idActive);
        return discos.stream().map(DiscoDTO::new).collect(Collectors.toList());
    }
}
