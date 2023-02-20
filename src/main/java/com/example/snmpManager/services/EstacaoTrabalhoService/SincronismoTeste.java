package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.repositories.InterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SincronismoTeste implements Runnable{

    @Autowired
    private EstacaoTrabalhoService service;

    @Override
    public void run() {

            System.out.println("nome thead " + Thread.currentThread().getName());

           service.synchronizeWorstationTeste("192.168.0.106");
            System.out.println("teste6666" + "inter");


    }
}
