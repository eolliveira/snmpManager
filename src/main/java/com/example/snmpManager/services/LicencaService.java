package com.example.snmpManager.services;

import com.example.snmpManager.dto.LicencaDTO.LicencaDTO;
import com.example.snmpManager.entities.*;
import com.example.snmpManager.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicencaService {

    @Autowired
    private LicencaRepository licencaRepository;

    @Transactional
    public List<LicencaDTO> findAll() {
        List<LicencaEntity> licencas = licencaRepository.findAll();
        return licencas.stream().map(LicencaDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public LicencaDTO insertNewLicense(LicencaDTO dto) {
        LicencaEntity licenca = new LicencaEntity(dto);
        licenca = licencaRepository.save(licenca);
        return new LicencaDTO(licenca);
    }
}
