package com.example.snmpManager.services.LicencaService;

import com.example.snmpManager.dto.LicencaDTO.LicencaInsertDTO;
import com.example.snmpManager.entities.*;
import com.example.snmpManager.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class NewLicenseService {

    @Autowired
    private LicencaRepository licencaRepository;

    @Transactional
    public LicencaInsertDTO insertNewLicense(LicencaInsertDTO dto) {
        LicencaEntity licenca = new LicencaEntity(dto);
        licenca = licencaRepository.save(licenca);
        return new LicencaInsertDTO(licenca);
    }
}
