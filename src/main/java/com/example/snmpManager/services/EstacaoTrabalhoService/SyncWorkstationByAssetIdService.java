package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoSynchronizeDTO;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SyncWorkstationByAssetIdService {

    private final EstacaoTrabalhoRepository estacaoTrabalhoRepository;
    private final GetDataFromWorkstationService getDataFromWorkstationService;

    @Transactional
    public void synchronizeWorstation(Long idAtivo) {

        Optional<EstacaoTrabalhoEntity> opt = estacaoTrabalhoRepository.findById(idAtivo);
        EstacaoTrabalhoEntity estacaoTrabalho = opt.orElseThrow(() -> new ResourceNotFoundException("Estação id: " + idAtivo + " não encontrada."));

        WorkstationObject objAgent = new WorkstationObject();

        for (InterfaceEntity i : estacaoTrabalho.getInterfaces()) {
            if (i.getEnderecoIp() != "" && i.getEnderecoIp() != null) {
                //busca informações do ativo pelo ip
                WorkstationObject obj = getDataFromWorkstationService.getWorkstationData(i.getEnderecoIp());
                if (obj.getFabricante() != null) {
                    objAgent = obj;
                }
            }
        }

        if (objAgent.getFabricante() == null) {
            throw new ResourceNotFoundException("Nenhum endereco IPV4 definido para estação Id: " + idAtivo);
        }

        EstacaoTrabalhoSynchronizeDTO dto = new EstacaoTrabalhoSynchronizeDTO(objAgent);

        estacaoTrabalho.setFabricante(dto.getFabricante());
        estacaoTrabalho.setNumeroSerie(dto.getNumeroSerie());
        estacaoTrabalho.setModelo(dto.getModelo());
        estacaoTrabalho.setGateway(dto.getGateway());
        estacaoTrabalho.setDnsList(dto.getDnsList());
        estacaoTrabalho.setSistemaOperacional(dto.getSistemaOperacional());
        estacaoTrabalho.setArquiteturaSo(dto.getArquiteturaSo());
        estacaoTrabalho.setProcessador(dto.getProcessador());
        estacaoTrabalho.setMemoriaRam(dto.getMemoriaRam());
        estacaoTrabalho.setNomeHost(dto.getNomeHost());
        estacaoTrabalho.setDominio(dto.getDominio());
        estacaoTrabalho.setUltimoUsuarioLogado(dto.getUltimoUsuarioLogado());
        estacaoTrabalho.setUltimoSincronismo(Instant.now());

        List<InterfaceEntity> listaAtualizada = new ArrayList<>();
        dto.getInterfaces().stream().map(interfaceDto -> listaAtualizada.add(new InterfaceEntity(interfaceDto, estacaoTrabalho))).collect(Collectors.toList());

        List<String> fabricantes1 = estacaoTrabalho.getInterfaces().stream().map(InterfaceEntity::getFabricante).collect(Collectors.toList());
        List<String> fabricantes2 = listaAtualizada.stream().map(InterfaceEntity::getFabricante).collect(Collectors.toList());

        //compara as listas pelo fabricante
        if (!fabricantes1.equals(fabricantes2)) {
            estacaoTrabalho.getInterfaces().clear();
            estacaoTrabalho.getInterfaces().addAll(listaAtualizada);
            estacaoTrabalhoRepository.save(estacaoTrabalho);
        }

        estacaoTrabalhoRepository.save(estacaoTrabalho);

    }

}
