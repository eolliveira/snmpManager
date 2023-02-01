package com.example.snmpManager.controllers;

import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.objects.WindowsObject;
import com.example.snmpManager.services.EstacaoTrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/win")
public class  WindowsController {

    @Autowired
    private EstacaoTrabalhoService windowsService;

    @GetMapping(value = "/{ipAddress}")
    public ResponseEntity<WindowsObject> findAll(@PathVariable String ipAddress) {
        WindowsObject win = windowsService.getObjectData(ipAddress);
        return ResponseEntity.ok(win);
    }

    //add nova estação de trabalho
    @PostMapping()
    public ResponseEntity<EstacaoTrabalhoEntity> insertNewWorkStation(@RequestBody EstacaoTrabalhoEntity dto) {
        EstacaoTrabalhoEntity estacaoCriada = windowsService.insertNewWorkStation(dto);
        return ResponseEntity.ok(estacaoCriada);
    }
}
