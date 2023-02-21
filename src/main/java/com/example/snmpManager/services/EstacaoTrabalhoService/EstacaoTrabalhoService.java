package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoBasicDTO;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.exceptions.DataBaseException;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstacaoTrabalhoService {

    @Autowired
    private EstacaoTrabalhoRepository estacaoTrabalhoRepository;


    public List<EstacaoTrabalhoBasicDTO> findAll() {
        List<EstacaoTrabalhoEntity> estacoes = estacaoTrabalhoRepository.findAll();
        return estacoes.stream().map(EstacaoTrabalhoBasicDTO::new).collect(Collectors.toList());
    }




    public void deleteWorkstation(Long id) {
        try {
            estacaoTrabalhoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Estação id: " + id + " não encontrada.");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Violação da integridade do banco de dados");
        }
    }

}
