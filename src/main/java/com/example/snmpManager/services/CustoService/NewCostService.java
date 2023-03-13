package com.example.snmpManager.services.CustoService;

import com.example.snmpManager.dto.CustoDTO.CustoDTO;
import com.example.snmpManager.dto.CustoDTO.CustoInsertDTO;
import com.example.snmpManager.entities.AtivoEntity;
import com.example.snmpManager.entities.CustoEntity;
import com.example.snmpManager.entities.UsuarioEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.AtivoRepository;
import com.example.snmpManager.repositories.CustoRepository;
import com.example.snmpManager.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NewCostService {

    private final CustoRepository custoRepository;
    private final AtivoRepository ativoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public CustoDTO insertNewCost(CustoInsertDTO dto) {

        Optional<AtivoEntity> optAtivo = ativoRepository.findById(dto.getAtivo().getId());
        AtivoEntity ativo = optAtivo.orElseThrow(() -> new ResourceNotFoundException("Ativo id: " + dto.getAtivo().getId() + " não encontrado."));

        //TODO(Deve ser informado o id do usuário logado - implementar )
        Optional<UsuarioEntity> optUser = usuarioRepository.findById(dto.getUsuario().getId());
        UsuarioEntity usuario = optUser.orElseThrow(() -> new ResourceNotFoundException("Usuário id: " + dto.getUsuario().getId() + " não encontrado."));


        CustoEntity custo = new CustoEntity(ativo, usuario, dto);
        custoRepository.save(custo);
        return new CustoDTO(custo);
    }
}
