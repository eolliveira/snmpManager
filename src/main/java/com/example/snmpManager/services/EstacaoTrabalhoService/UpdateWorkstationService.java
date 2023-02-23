package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.DiscoParticaoDTO.DiscoParticaoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoSynchronizeDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceAtivoDTO;
import com.example.snmpManager.entities.DiscoEntity;
import com.example.snmpManager.entities.DiscoParticaoEntity;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.repositories.DiscoParticaoRepository;
import com.example.snmpManager.repositories.DiscoRepository;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import com.example.snmpManager.repositories.InterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateWorkstationService {

    @Autowired
    EstacaoTrabalhoRepository estacaoTrabalhoRepository;

    @Autowired
    InterfaceRepository interfaceRepository;

    @Autowired
    DiscoRepository discoRepository;

    @Autowired
    DiscoParticaoRepository discoParticaoRepository;


    @Transactional
    public EstacaoTrabalhoSynchronizeDTO updateWorkStation(Long idAtivo, EstacaoTrabalhoSynchronizeDTO dto) {
//TODO(DUPLICANDO INTERFACES)
//        Optional<EstacaoTrabalhoEntity> opt = estacaoTrabalhoRepository.findById(idAtivo);
//        EstacaoTrabalhoEntity estacaoTrabalho = opt.orElseThrow(() -> new ResourceNotFoundException("Estação id: " + idAtivo + " não encontrada."));

        EstacaoTrabalhoEntity estacaoTrabalho = estacaoTrabalhoRepository.getReferenceById(idAtivo);

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


        //recupera todos as interfaces e discos da estação
        List<InterfaceEntity> interfaces = interfaceRepository.findAllByEstacaoTrabalho_Id(estacaoTrabalho.getId());
        List<DiscoEntity> discos = discoRepository.findAllByEstacaoTrabalho_Id(estacaoTrabalho.getId());

        if (!interfaces.isEmpty() && !discos.isEmpty()) {
            interfaceRepository.deleteAll(interfaces);
        }

        if (!discos.isEmpty()) {
            discoRepository.deleteAll(discos);
        }

        //adiciona interfaces atualizadas
        for (InterfaceAtivoDTO i : dto.getInterfaces()) {
            InterfaceEntity inter = new InterfaceEntity();
            inter.setNomeLocal(i.getNomeLocal());
            inter.setFabricante(i.getFabricante());
            inter.setEnderecoMac(i.getEnderecoMac());
            inter.setEnderecoIp(i.getEnderecoIp());
            inter.setMascaraSubRede(i.getMascaraSubRede());
            inter.setEstacaoTrabalho(estacaoTrabalho);
            interfaceRepository.save(inter);
        }

        //adiciona discos atualizados
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

        return new EstacaoTrabalhoSynchronizeDTO(estacaoTrabalho);
    }


}
