package com.example.snmpManager.services.ImpressoraService;

import com.example.snmpManager.exceptions.DataBaseException;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.ImpressoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class RemovePrinterService {

    @Autowired
    ImpressoraRepository impressoraRepository;
    public void deletePrinter(Long id) {
        try {
            impressoraRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Impressora id: " + id + " não encontrada.");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Violação da integridade do banco de dados");
        }
    }

}
