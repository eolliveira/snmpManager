package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.LicencaDTO.LicencaInsertDTO;
import com.example.snmpManager.services.LicencaService.FindAllLicenseService;
import com.example.snmpManager.services.LicencaService.NewLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/license")
public class LicencaController {

    @Autowired
    private NewLicenseService newLicenseService;

    @Autowired
    private FindAllLicenseService findAllLicenseService;

    @GetMapping()
    public ResponseEntity<List<LicencaInsertDTO>> findAllLicenses() {
        List<LicencaInsertDTO> licencas = findAllLicenseService.findAllLicense();
        return ResponseEntity.ok(licencas);
    }

    @PostMapping()
    public ResponseEntity<LicencaInsertDTO> insertNewLicense(@RequestBody LicencaInsertDTO dto) {
        LicencaInsertDTO licenca = newLicenseService.insertNewLicense(dto);
        return ResponseEntity.ok(licenca);
    }

}
