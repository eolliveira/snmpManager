package com.example.snmpManager.services.LicencaService;

import com.example.snmpManager.dto.LicencaDTO.LicencaDTO;
import com.example.snmpManager.entities.LicencaEntity;
import com.example.snmpManager.repositories.LicencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindTheAssetLicencesService {

    @Autowired
    LicencaRepository licencaRepository;

    public List<LicencaDTO> findAllLicenses(Long idActive) {
        List<LicencaEntity> licencas = licencaRepository.findAllByAtivoId(idActive);
        return licencas.stream().map(LicencaDTO::new).collect(Collectors.toList());
    }
}
