package com.example.snmpManager.services;

import com.example.snmpManager.dto.*;
import com.example.snmpManager.entities.*;
import com.example.snmpManager.exceptions.InvalidAddressExecption;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.mibs.WindowsMIB;
import com.example.snmpManager.objects.WindowsObject;
import com.example.snmpManager.repositories.*;
import com.example.snmpManager.util.AddressValidation;
import org.snmp4j.smi.OID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LicencaService {

    @Autowired
    private LicencaRepository licencaRepository;

    @Transactional
    public List<LicencaDTO> findAll() {
        List<LicencaEntity> licencas = licencaRepository.findAll();
        return licencas.stream().map(l -> new LicencaDTO(l)).collect(Collectors.toList());
    }

    @Transactional
    public LicencaDTO insertNewLicense(LicencaDTO dto) {
        LicencaEntity licenca = new LicencaEntity(dto);
        licenca = licencaRepository.save(licenca);
        return new LicencaDTO(licenca);
    }

}
