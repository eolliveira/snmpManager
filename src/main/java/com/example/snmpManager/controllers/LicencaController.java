package com.example.snmpManager.controllers;

import com.example.snmpManager.dto.LicencaDTO.LicencaInsertDTO;
import com.example.snmpManager.dto.LicencaDTO.LicencaLinkDTO;
import com.example.snmpManager.dto.LicencaDTO.LicencaUpdateDTO;
import com.example.snmpManager.services.LicencaService.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/license")
public class LicencaController {

    private final NewLicenseService newLicenseService;
    private final FindAllLicenseService findAllLicenseService;
    private final UpdateLicenseService updateLicenseService;
    private final LinkLicenseService linkLicenseService;
    private final UnlinkLicenseService unlinkLicenseService;

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

    @PutMapping(value = "/{licenseId}")
    public ResponseEntity<LicencaUpdateDTO> updateLicense(@PathVariable Long licenseId, @RequestBody LicencaUpdateDTO dto) {
        dto = updateLicenseService.updateLicense(licenseId, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/linkActive")
    public void linkActive(@RequestBody LicencaLinkDTO dto) {
        linkLicenseService.linkLicense(dto);
    }

    @PutMapping(value = "/unlinkActive")
    public void unlinkActive(@RequestBody LicencaLinkDTO dto) {
        unlinkLicenseService.unlinkLicense(dto);
    }


}
