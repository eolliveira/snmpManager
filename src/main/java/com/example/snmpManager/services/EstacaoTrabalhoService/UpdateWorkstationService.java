package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoUpdateDTO;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.entities.UsuarioEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import com.example.snmpManager.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateWorkstationService {

    private final EstacaoTrabalhoRepository estacaoTrabalhoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public EstacaoTrabalhoUpdateDTO updateWorkStation(Long idAtivo, EstacaoTrabalhoUpdateDTO dto) {
        Optional<EstacaoTrabalhoEntity> opt = estacaoTrabalhoRepository.findById(idAtivo);
        EstacaoTrabalhoEntity estacaoTrabalho = opt.orElseThrow(() -> new ResourceNotFoundException("Estação id: " + idAtivo + " não encontrada."));

        estacaoTrabalho.setNome(dto.getNome());
        estacaoTrabalho.setDescricao(dto.getDescricao());
        estacaoTrabalho.setInativo(dto.getInativo());
        estacaoTrabalho.setDtAquisicao(dto.getDtAquisicao());
        estacaoTrabalho.setDtVencimentoGarantia(dto.getDtVencimentoGarantia());
        estacaoTrabalho.setDtExpiracao(dto.getDtExpiracao());
        estacaoTrabalho.setValorCompra(dto.getValorCompra());
        estacaoTrabalho.setFornecedor(dto.getFornecedor());
        estacaoTrabalho.setObservacao(dto.getObservacao());
        estacaoTrabalho.setNomeHost(dto.getNomeHost());

        if(dto.getUsuario() != null ) {
            Optional<UsuarioEntity> userOpt = usuarioRepository.findById(dto.getUsuario().getId());
            UsuarioEntity usuario = userOpt.orElseThrow(() -> new ResourceNotFoundException("Usuário id: " + dto.getUsuario().getId() + " não encontrado"));
            estacaoTrabalho.setUsuario(usuario);
        }

        estacaoTrabalhoRepository.save(estacaoTrabalho);

        return new EstacaoTrabalhoUpdateDTO(estacaoTrabalho);
    }


}
