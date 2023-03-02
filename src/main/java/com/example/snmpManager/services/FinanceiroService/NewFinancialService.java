package com.example.snmpManager.services.FinanceiroService;

import com.example.snmpManager.dto.FinanceiroDTO.FinanceiroDTO;
import com.example.snmpManager.dto.FinanceiroDTO.FinanceiroInsertDTO;
import com.example.snmpManager.entities.AtivoEntity;
import com.example.snmpManager.entities.FinanceiroEntity;
import com.example.snmpManager.entities.MovimentoEntity;
import com.example.snmpManager.entities.UsuarioEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.exceptions.UnprocesableEntityExecption;
import com.example.snmpManager.repositories.AtivoRepository;
import com.example.snmpManager.repositories.FinanceiroRepository;
import com.example.snmpManager.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NewFinancialService {

    private final FinanceiroRepository financeiroRepository;
    private final AtivoRepository ativoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public FinanceiroDTO insertNewFinancial(FinanceiroInsertDTO dto) {

        Optional<AtivoEntity> optAtivo = ativoRepository.findById(dto.getAtivo().getId());
        AtivoEntity ativo = optAtivo.orElseThrow(() -> new ResourceNotFoundException("Ativo id: " + dto.getAtivo().getId() + " não encontrado."));

        //TODO(Deve ser informado o id do usuário logado - implementar )
        Optional<UsuarioEntity> optUser = usuarioRepository.findById(dto.getUsuario().getId());
        UsuarioEntity usuario = optUser.orElseThrow(() -> new ResourceNotFoundException("Usuário id: " + dto.getUsuario().getId() + " não encontrado."));


        FinanceiroEntity financeiro = new FinanceiroEntity(ativo, usuario, dto);
        financeiroRepository.save(financeiro);
        return new FinanceiroDTO(financeiro);
    }
}
