package com.example.snmpManager.services.LicencaService;

import com.example.snmpManager.dto.LicencaDTO.LicencaInsertDTO;
import com.example.snmpManager.entities.LicencaEntity;
import com.example.snmpManager.repositories.LicencaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllLicenseService {

    private final LicencaRepository licencaRepository;

    @Transactional
    public List<LicencaInsertDTO> findAllLicense() {
        //TODO(Paginação)
        List<LicencaEntity> licencas = licencaRepository.findAll();
        return licencas.stream().map(LicencaInsertDTO::new).collect(Collectors.toList());
    }
}
