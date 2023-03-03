package com.example.snmpManager.services.MovimentoService;

import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoDTO;
import com.example.snmpManager.dto.MotivoAtivoDTO.MovimentoInsertDTO;
import com.example.snmpManager.entities.AtivoEntity;
import com.example.snmpManager.entities.MovimentoEntity;
import com.example.snmpManager.entities.UsuarioEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.exceptions.UnprocesableEntityExecption;
import com.example.snmpManager.repositories.AtivoRepository;
import com.example.snmpManager.repositories.MovimentoRepository;
import com.example.snmpManager.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewMovimentService {

    private final MovimentoRepository movimentoRepository;
    private final AtivoRepository ativoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public MovimentoDTO insertNewMoviment(MovimentoInsertDTO dto) {
        Optional<AtivoEntity> optAtivo = ativoRepository.findById(dto.getAtivo().getId());
        AtivoEntity ativo = optAtivo.orElseThrow(() -> new ResourceNotFoundException("Ativo id: " + dto.getAtivo().getId() + " não encontrado."));

        if(ativo.getStatus() == dto.getStatusAtivo()){
            throw new UnprocesableEntityExecption("Status [" + dto.getStatusAtivo() + "] ja esta definido para o Ativo id: " + ativo.getId());
        }

        //TODO(Deve ser informado o id do usuário logado - implementar )
        Optional<UsuarioEntity> optUser = usuarioRepository.findById(dto.getUsuario().getId());
        UsuarioEntity usuario = optUser.orElseThrow(() -> new ResourceNotFoundException("Usuário id: " + dto.getUsuario().getId() + " não encontrado."));

        MovimentoEntity movimento = new MovimentoEntity(dto, ativo, usuario);
        movimentoRepository.save(movimento);

        //altera o status do ativo
        ativo.setStatus(dto.getStatusAtivo());
        ativoRepository.save(ativo);

        return new MovimentoDTO(movimento);

    }
}
