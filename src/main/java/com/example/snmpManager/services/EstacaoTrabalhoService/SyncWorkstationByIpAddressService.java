package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.DiscoParticaoDTO.DiscoParticaoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoSynchronizeDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceDTO;
import com.example.snmpManager.entities.DiscoEntity;
import com.example.snmpManager.entities.DiscoParticaoEntity;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
import com.example.snmpManager.repositories.DiscoParticaoRepository;
import com.example.snmpManager.repositories.DiscoRepository;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import com.example.snmpManager.repositories.InterfaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SyncWorkstationByIpAddressService {

    private final EstacaoTrabalhoRepository estacaoTrabalhoRepository;
    private final InterfaceRepository interfaceRepository;
    private final DiscoRepository discoRepository;
    private final DiscoParticaoRepository discoParticaoRepository;

    @Transactional
    public void synchronizeWorstation(String ipAdrress, WorkstationObject object) {

        InterfaceEntity interfaceEntitiy = interfaceRepository.findByEnderecoIp(ipAdrress);
        Long idAtivo = interfaceEntitiy.getEstacaoTrabalho().getId();

        Optional<EstacaoTrabalhoEntity> opt = estacaoTrabalhoRepository.findById(idAtivo);
        EstacaoTrabalhoEntity estacaoTrabalho = opt.orElseThrow(() -> new ResourceNotFoundException("Estação id: " + idAtivo + " não encontrada."));


        EstacaoTrabalhoSynchronizeDTO dto = new EstacaoTrabalhoSynchronizeDTO(object);

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
        estacaoTrabalho = estacaoTrabalhoRepository.save(estacaoTrabalho);

        //TODO(Verificar alternativa para modificar lista )
        interfaceRepository.deleteAllByEstacaoTrabalhoId(estacaoTrabalho.getId());
        discoRepository.deleteAllByEstacaoTrabalho_Id(estacaoTrabalho.getId());

        for (InterfaceDTO i : dto.getInterfaces()) {
            InterfaceEntity inter = new InterfaceEntity();
            inter.setNomeLocal(i.getNomeLocal());
            inter.setFabricante(i.getFabricante());
            inter.setEnderecoMac(i.getEnderecoMac());
            inter.setEnderecoIp(i.getEnderecoIp());
            inter.setMascaraSubRede(i.getMascaraSubRede());
            inter.setEstacaoTrabalho(estacaoTrabalho);
            interfaceRepository.save(inter);
        }

        for (DiscoDTO d : dto.getDiscos()) {
            DiscoEntity disco = new DiscoEntity();
            disco.setNome(d.getNome());
            disco.setModelo(d.getModelo());
            disco.setNumeroSerie(d.getNumeroSerie());
            disco.setCapacidade(d.getCapacidade());
            disco.setEstacaoTrabalho(estacaoTrabalho);

            discoRepository.save(disco);

            for (DiscoParticaoDTO dpd : d.getParticoes()) {
                DiscoParticaoEntity dpe = new DiscoParticaoEntity();
                dpe.setCapacidade(dpd.getCapacidade());
                dpe.setUsado(dpd.getUsado());
                dpe.setPontoMontagem(dpd.getPontoMontagem());
                dpe.setDisco(disco);

                discoParticaoRepository.save(dpe);
            }
        }


    }


}
