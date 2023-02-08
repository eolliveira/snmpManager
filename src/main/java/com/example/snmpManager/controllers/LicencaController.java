package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.LicencaDTO;
import com.example.snmpManager.services.LicencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/license")
public class LicencaController {

    @Autowired
    private LicencaService licencaService;


    @GetMapping
    public ResponseEntity<List<LicencaDTO>> findAll() {
        List<LicencaDTO> licencas = licencaService.findAll();
        return ResponseEntity.ok(licencas);
    }

    @PostMapping()
    public ResponseEntity<LicencaDTO> insertNewWorkStation(@RequestBody LicencaDTO dto) {
        LicencaDTO estacaoCriada = licencaService.insertNewLicense(dto);
        return ResponseEntity.ok(estacaoCriada);
    }

}
