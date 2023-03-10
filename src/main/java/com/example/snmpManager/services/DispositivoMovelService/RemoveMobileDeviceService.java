package com.example.snmpManager.services.DispositivoMovelService;

import com.example.snmpManager.exceptions.DataBaseException;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.DispositivoMovelRepository;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class RemoveMobileDeviceService {

    @Autowired
    DispositivoMovelRepository dispositivoMovelRepository;

    public void deleteMobileDevice(Long id) {
        try {
            dispositivoMovelRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Dispositivo id: " + id + " não encontrado.");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Violação da integridade do banco de dados");
        }
    }

}
