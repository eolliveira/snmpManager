package com.example.snmpManager.services;

import com.example.snmpManager.entities.InterfaceAtivoEntity;
import com.example.snmpManager.repositories.InterfaceAtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InterfaceAtivoService {

    @Autowired
    private InterfaceAtivoRepository repository;

    //busca as interfaces da estação pelo id
    @Transactional
    public List<InterfaceAtivoEntity> getInterfacesByActiveId(Long id) {
        List<InterfaceAtivoEntity> interfaces = repository.findAllByEstacaoTrabalho_Id(id);
        return interfaces;
    }


}
