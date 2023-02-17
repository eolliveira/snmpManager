package com.example.snmpManager.services.MovimentoService;

import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoAtivoDTO;
import com.example.snmpManager.entities.AtivoEntity;
import com.example.snmpManager.entities.MovimentoEntity;
import com.example.snmpManager.entities.UsuarioEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.AtivoRepository;
import com.example.snmpManager.repositories.MovimentoAtivoRepository;
import com.example.snmpManager.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MovimentoService {

    @Autowired
    private MovimentoAtivoRepository movimentoRepository;

    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    public void insertNewMoviment(MovimentoAtivoDTO dto) {
        Optional<AtivoEntity> optAtivo = ativoRepository.findById(dto.getAtivo().getId());
        AtivoEntity ativo = optAtivo.orElseThrow(() -> new ResourceNotFoundException("Ativo id: " + dto.getAtivo().getId() + " não encontrado."));

        Optional<UsuarioEntity> optUser = usuarioRepository.findById(dto.getUsuario().getId());
        UsuarioEntity usuario = optUser.orElseThrow(() -> new ResourceNotFoundException("Usuário id: " + dto.getUsuario().getId() + " não encontrado."));

        MovimentoEntity movimento = new MovimentoEntity(dto, ativo, usuario);

        movimentoRepository.save(movimento);
    }
}
