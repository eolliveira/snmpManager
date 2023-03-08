package com.example.snmpManager.services.ImpressoraService;

import com.example.snmpManager.dto.ImpressoraDTO.ImpressoraSyncronizedDTO;
import com.example.snmpManager.entities.*;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.exceptions.UnableToGetDeviceDataException;
import com.example.snmpManager.objects.PrinterObject;
import com.example.snmpManager.repositories.ImpressoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

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
                    if (obj.getNomeHost() != null) {
                        objAgent = obj;
                        break;
                    }
                } catch (Exception ignored) {
                }
            }
        }

        if (objAgent.getNomeHost() == null && objAgent.getEnderecoIp() == null)
            throw new UnableToGetDeviceDataException("Não foi possivel sincronizar os dados da impressora");

        ImpressoraSyncronizedDTO dto = new ImpressoraSyncronizedDTO(objAgent);
        impressora.setNomeHost(dto.getNomeHost());
        impressora.setDominio(dto.getDominio());
        impressora.setNumeroSerie(dto.getNumeroSerie());
        impressora.setModelo(dto.getModelo());
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
