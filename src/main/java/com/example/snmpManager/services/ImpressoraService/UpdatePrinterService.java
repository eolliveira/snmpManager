package com.example.snmpManager.services.ImpressoraService;

import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraDTO;
import com.example.snmpManager.dto.InterfaceDTO.InterfaceDTO;
import com.example.snmpManager.entities.ImpressoraEntity;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.ImpressoraRepository;
import com.example.snmpManager.util.AddressValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UpdatePrinterService {
    private final ImpressoraRepository impressoraRepository;

    private final AddressValidation addressValidation;

    @Transactional
    public ImpressoraDTO updatePrinter(Long idAtivo, ImpressoraDTO dto) {
        Optional<ImpressoraEntity> opt = impressoraRepository.findById(idAtivo);
        ImpressoraEntity impressora = opt.orElseThrow(() -> new ResourceNotFoundException("Impressora id: " + idAtivo + " não encontrada."));

        List<InterfaceDTO> interfacesDto = new ArrayList<>();
        interfacesDto.add(new InterfaceDTO(null, null, null, dto.getEnderecoMac(), dto.getEnderecoIp(), dto.getMascaraSubRede()));
        interfacesDto.stream().map(i -> dto.getInterfaces().add(new InterfaceDTO(i))).collect(Collectors.toList());

        addressValidation.addressAlreadyExists(dto);

        impressora.setNome(dto.getNome());
        impressora.setNomeHost(dto.getNomeHost());
        impressora.setDescricao(dto.getDescricao());
        impressora.setInativo(dto.getInativo());
        impressora.setStatus(dto.getStatus());
        impressora.setTempoLigado(dto.getTempoLigado());
        impressora.setDtAquisicao(dto.getDtAquisicao());
        impressora.setDtVencimentoGarantia(dto.getDtVencimentoGarantia());
        impressora.setDtExpiracao(dto.getDtExpiracao());
        impressora.setValorCompra(dto.getValorCompra());
        impressora.setFornecedor(dto.getFornecedor());
        impressora.setObservacao(dto.getObservacao());
        impressora.setFabricante(dto.getFabricante());
        impressora.setNumeroSerie(dto.getNumeroSerie());
        impressora.setDominio(dto.getDominio());
        impressora.setModelo(dto.getModelo());
        impressora.setGateway(dto.getGateway());
        impressora.setTotalImpressoes(dto.getTotalImpressoes());

        impressora.getInterfaces().clear();
        impressora.getInterfaces().add(new InterfaceEntity(
                null,
                null,
                null,
                dto.getEnderecoMac(),
                dto.getEnderecoIp(),
                dto.getMascaraSubRede(),
                impressora
        ));

        return new ImpressoraDTO(impressora);
    }


}
