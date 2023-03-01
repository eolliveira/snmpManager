package com.example.snmpManager.services.DiscoService;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.entities.DiscoEntity;
import com.example.snmpManager.repositories.DiscoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindDiscsWorkstationService {

    private final DiscoRepository discoRepository;

    public List<DiscoDTO> findAllDiscs(Long idActive) {
        List<DiscoEntity> discos = discoRepository.findAllByEstacaoTrabalho_Id(idActive);
        return discos.stream().map(DiscoDTO::new).collect(Collectors.toList());
    }
}
