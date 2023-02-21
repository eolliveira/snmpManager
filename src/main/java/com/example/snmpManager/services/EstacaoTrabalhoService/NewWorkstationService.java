package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.DiscoParticaoDTO.DiscoParticaoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceAtivoDTO;
import com.example.snmpManager.entities.DiscoEntity;
import com.example.snmpManager.entities.DiscoParticaoEntity;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.repositories.DiscoParticaoRepository;
import com.example.snmpManager.repositories.DiscoRepository;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import com.example.snmpManager.repositories.InterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class NewWorkstationService {

    @Autowired
    private EstacaoTrabalhoRepository estacaoTrabalhoRepository;

    @Autowired
    private InterfaceRepository interfaceRepository;

    @Autowired
    private DiscoRepository discoRepository;

    @Autowired
    private DiscoParticaoRepository discoParticaoRepository;


    @Transactional
    public EstacaoTrabalhoDTO insertNewWorkStation(EstacaoTrabalhoDTO dto) {

        EstacaoTrabalhoEntity estacao = new EstacaoTrabalhoEntity(dto);

        estacao = estacaoTrabalhoRepository.save(estacao);

        for (InterfaceAtivoDTO i : dto.getInterfaces()) {
            InterfaceEntity inter = new InterfaceEntity();
            inter.setNomeLocal(i.getNomeLocal());
            inter.setFabricante(i.getFabricante());
            inter.setEnderecoMac(i.getEnderecoMac());
            inter.setEnderecoIp(i.getEnderecoIp());
            inter.setMascaraSubRede(i.getMascaraSubRede());
            inter.setEstacaoTrabalho(estacao);

            interfaceRepository.save(inter);
        }

        for (DiscoDTO d : dto.getDiscos()) {
            DiscoEntity disco = new DiscoEntity();
            disco.setNome(d.getNome());
            disco.setModelo(d.getModelo());
            disco.setNumeroSerie(d.getNumeroSerie());
            disco.setCapacidade(d.getCapacidade());
            disco.setEstacaoTrabalho(estacao);

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

        return new EstacaoTrabalhoDTO(estacao);
    }
}
