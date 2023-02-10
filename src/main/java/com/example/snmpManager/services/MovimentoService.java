package com.example.snmpManager.services;

import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoAtivoDTO;
import com.example.snmpManager.entities.AtivoEntity;
import com.example.snmpManager.entities.MovimentoEntity;
import com.example.snmpManager.entities.UsuarioEntity;
import com.example.snmpManager.repositories.MovimentoAtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MovimentoService {

    @Autowired
    private MovimentoAtivoRepository movimentoRepository;


    @Transactional
    public MovimentoAtivoDTO insertNewMoviment(MovimentoAtivoDTO dto) {
        MovimentoEntity movimento = new MovimentoEntity();

        movimento.setId(dto.getId());
        movimento.setDescricao(dto.getDescricao());
        movimento.setDtMovimento(dto.getDtMovimento());
        movimento.setStatusAtivoAnterior(dto.getStatusAtivoAnterior());
        movimento.setStatusAtivo(dto.getStatusAtivo());
        movimento.setAtivo(dto.getAtivo());
        movimento.setUsuario(dto.getUsuario());
        //TODO(verificar relacionamento entre a classe abstrata ativo)


        movimento = movimentoRepository.save(movimento);
        return new MovimentoAtivoDTO(movimento);
    }
}
