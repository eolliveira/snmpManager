package com.example.snmpManager.services.AtivoService;

import com.example.snmpManager.exceptions.DataBaseException;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class RemoveActiveService {

    @Autowired
    AtivoRepository ativoRepository;

    public void deleteActive(Long id) {
        try {
            ativoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Ativo id: " + id + " não encontrada.");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Violação da integridade do banco de dados");
        }
    }

}
