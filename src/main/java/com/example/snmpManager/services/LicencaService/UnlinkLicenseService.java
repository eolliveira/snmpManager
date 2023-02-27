package com.example.snmpManager.services.LicencaService;

import com.example.snmpManager.dto.LicencaDTO.LicencaLinkDTO;
import com.example.snmpManager.entities.AtivoEntity;
import com.example.snmpManager.entities.LicencaEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UnlinkLicenseService {

    @Autowired
    private AtivoRepository ativoRepository;

    @Transactional
    public void unlinkLicense(LicencaLinkDTO dto) {
        Optional<AtivoEntity> ativoOpt = ativoRepository.findById(dto.getAtivoId());
        AtivoEntity ativo = ativoOpt.orElseThrow(() -> new ResourceNotFoundException("Ativo id: " + dto.getAtivoId() + " não encontrado."));

        Optional<AtivoEntity> licencaOpt = ativoRepository.findById(dto.getLicencaId());
        AtivoEntity licenca = licencaOpt.orElseThrow(() -> new ResourceNotFoundException("Licença id: " + dto.getLicencaId() + " não encontrada."));

        if (ativo.getLicencas().contains(licenca)) {
            ativo.getLicencas().remove(licenca);
            ativoRepository.save(ativo);
        }

    }
}
