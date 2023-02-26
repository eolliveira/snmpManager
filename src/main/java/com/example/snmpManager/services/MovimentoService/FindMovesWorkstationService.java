package com.example.snmpManager.services.MovimentoService;

import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoDTO;
import com.example.snmpManager.entities.MovimentoEntity;
import com.example.snmpManager.repositories.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindMovesWorkstationService {

    @Autowired
    MovimentoRepository movimentoRepository;


    public List<MovimentoDTO> findAllMoves(Long idActive) {
        //TODO(Tratar exception)
        List<MovimentoEntity> movimentos = movimentoRepository.findAllByAtivo_Id(idActive);
        return movimentos.stream().map(MovimentoDTO::new).collect(Collectors.toList());
    }
}