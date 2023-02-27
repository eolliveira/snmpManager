package com.example.snmpManager.services.LicencaService;

import com.example.snmpManager.dto.LicencaDTO.LicencaUpdateDTO;
import com.example.snmpManager.entities.LicencaEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.LicencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UpdateLicenseService {

    @Autowired
    private LicencaRepository licencaRepository;

    @Transactional
    public LicencaUpdateDTO updateLicense(Long licencaId, LicencaUpdateDTO dto) {

        Optional<LicencaEntity> opt = licencaRepository.findById(licencaId);
        LicencaEntity licenca = opt.orElseThrow(() -> new ResourceNotFoundException("Licença id: " + licencaId + " não encontrada."));

        licenca.setNome(dto.getNome());
        licenca.setFabricante(dto.getFabricante());
        licenca.setModelo(dto.getModelo());
        licenca.setDescricao(dto.getDescricao());
        licenca.setNumeroSerie(dto.getNumeroSerie());
        licenca.setInativo(dto.getInativo());
        licenca.setDtAquisicao(dto.getDtAquisicao());
        licenca.setDtVencimentoGarantia(dto.getDtVencimentoGarantia());
        licenca.setDtExpiracao(dto.getDtExpiracao());
        licenca.setValorCompra(dto.getValorCompra());
        licenca.setFornecedor(dto.getFornecedor());
        licenca.setObservacao(dto.getObservacao());
        licenca.setSoftware(dto.getSoftware());
        licenca.setQtdLicencas(dto.getQtdLicencas());
        licenca.setChaveLicenca(dto.getChaveLicenca());
        licencaRepository.save(licenca);

        return new LicencaUpdateDTO(licenca);
    }
}
