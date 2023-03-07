package com.example.snmpManager.services.ImpressoraService;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.DiscoParticaoDTO.DiscoParticaoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoSynchronizeDTO;
import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraSyncronizedDTO;
import com.example.snmpManager.entities.*;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.exceptions.UnableToGetDeviceDataException;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
import com.example.snmpManager.objects.PrinterObject;
import com.example.snmpManager.repositories.DiscoParticaoRepository;
import com.example.snmpManager.repositories.DiscoRepository;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import com.example.snmpManager.repositories.ImpressoraRepository;
import com.example.snmpManager.services.EstacaoTrabalhoService.GetDataFromWorkstationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SyncPrinterByAssetIdService {
    private final ImpressoraRepository impressoraRepository;
    private final GetDataFromPrinterService getDataFromPrinterService;

    @Transactional
    public void synchronizePrinter(Long idAtivo) {

        Optional<ImpressoraEntity> opt = impressoraRepository.findById(idAtivo);
        ImpressoraEntity impressora = opt.orElseThrow(() -> new ResourceNotFoundException("Impressora id: " + idAtivo + " não encontrada."));

        PrinterObject objAgent = new PrinterObject();

        for (InterfaceEntity i : impressora.getInterfaces()) {
            if (!Objects.equals(i.getEnderecoIp(), "") && i.getEnderecoIp() != null) {
                try {
                    PrinterObject obj = getDataFromPrinterService.getPrinterData(i.getEnderecoIp());
                    if (obj.getNome() != null) {
                        objAgent = obj;
                        break;
                    }
                } catch (Exception ignored) {
                }
            }
        }

        if (objAgent.getNome() == null && objAgent.getEnderecoIp() == null)
            throw new UnableToGetDeviceDataException("Não foi possivel sincronizar os dados da impressora");

        ImpressoraSyncronizedDTO dto = new ImpressoraSyncronizedDTO(objAgent);
        impressora.setNome(dto.getNomeHost());
        impressora.setNumeroSerie(dto.getNumeroSerie());
        impressora.setModelo(dto.getModelo());
        impressora.setGateway(dto.getGateway());
        impressora.setGateway(dto.getGateway());
        impressora.setTempoLigado(dto.getTempoLigado());
        impressora.setTotalImpressoes(dto.getTotalImpressoes());
        impressora.setUltimoSincronismo(Instant.now());

        impressora.getInterfaces().clear();
        impressora.getInterfaces().add(new InterfaceEntity(
                null,
                null,
                null,
                objAgent.getEnderecoMac(),
                objAgent.getEnderecoIp(),
                objAgent.getMascaraSubRede(),
                impressora));

        impressoraRepository.save(impressora);

    }

}
