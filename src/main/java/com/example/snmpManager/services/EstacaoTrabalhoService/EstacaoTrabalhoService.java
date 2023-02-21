package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.DiscoParticaoDTO.DiscoParticaoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoBasicDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.WindowsDTO.EstacaoTrabalhoSynchronizeDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceAtivoDTO;
import com.example.snmpManager.entities.DiscoEntity;
import com.example.snmpManager.entities.DiscoParticaoEntity;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.exceptions.DataBaseException;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
import com.example.snmpManager.repositories.DiscoParticaoRepository;
import com.example.snmpManager.repositories.DiscoRepository;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import com.example.snmpManager.repositories.InterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstacaoTrabalhoService {

    @Autowired
    private EstacaoTrabalhoRepository estacaoTrabalhoRepository;

    @Autowired
    private WorkstationDataService workstationDataService;
    @Autowired
    private InterfaceRepository interfaceAtivoRepository;
    @Autowired
    private DiscoRepository discoAtivoRepository;
    @Autowired
    private DiscoParticaoRepository discoAtivoParticaoRepository;

    public List<EstacaoTrabalhoBasicDTO> findAll() {
        List<EstacaoTrabalhoEntity> estacoes = estacaoTrabalhoRepository.findAll();
        return estacoes.stream().map(EstacaoTrabalhoBasicDTO::new).collect(Collectors.toList());
    }




    @Transactional
    public void synchronizeWorstation(String ipAdrress) {

        //tratar exception
        InterfaceEntity interfaceAtivo = interfaceAtivoRepository.findByEnderecoIp(ipAdrress);

        //interfaceAtivo.getEstacaoTrabalho().getId();

    }

    @Transactional
    public void synchronizeWorstation(Long idAtivo) {

        Optional<EstacaoTrabalhoEntity> opt = estacaoTrabalhoRepository.findById(idAtivo);
        EstacaoTrabalhoEntity estacaoTrabalho = opt.orElseThrow(() -> new ResourceNotFoundException("Estação id: " + idAtivo + " não encontrada."));

        WorkstationObject objAgent = new WorkstationObject();

        for (InterfaceEntity i : estacaoTrabalho.getInterfaces()) {
            if (i.getEnderecoIp() != "" && i.getEnderecoIp() != null) {
                WorkstationObject obj = workstationDataService.getWorkstationData(i.getEnderecoIp());
                if (obj.getFabricante() != null) {
                    objAgent = obj;
                }
            }
        }

        if (objAgent.getFabricante() == null) {
            throw new ResourceNotFoundException("Nenhum endereco IPV4 definido para estação Id: " + idAtivo);
        }

        EstacaoTrabalhoSynchronizeDTO dto = new EstacaoTrabalhoSynchronizeDTO(objAgent);
        updateWorkStation(idAtivo, dto);
    }

    @Transactional
    public EstacaoTrabalhoSynchronizeDTO updateWorkStation(Long idAtivo, EstacaoTrabalhoSynchronizeDTO dto) {

        Optional<EstacaoTrabalhoEntity> opt = estacaoTrabalhoRepository.findById(idAtivo);
        EstacaoTrabalhoEntity estacaoTrabalho = opt.orElseThrow(() -> new ResourceNotFoundException("Estação id: " + idAtivo + " não encontrada."));

        //EstacaoTrabalhoEntity estacaoTrabalho = estacaoTrabalhoRepository.getReferenceById(idAtivo);

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
        List<InterfaceEntity> interfaces = interfaceAtivoRepository.findAllByEstacaoTrabalho_Id(estacaoTrabalho.getId());
        List<DiscoEntity> discos = discoAtivoRepository.findAllByEstacaoTrabalho_Id(estacaoTrabalho.getId());

        if (!interfaces.isEmpty() && !discos.isEmpty()) {
            //remove tudo()
            interfaceAtivoRepository.deleteAll(interfaces);
            discoAtivoRepository.deleteAll(discos);
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
            interfaceAtivoRepository.save(inter);
        }

        //adiciona discos atualizados
        for (DiscoDTO d : dto.getDiscos()) {
            DiscoEntity disco = new DiscoEntity();
            disco.setNome(d.getNome());
            disco.setModelo(d.getModelo());
            disco.setNumeroSerie(d.getNumeroSerie());
            disco.setCapacidade(d.getCapacidade());
            disco.setEstacaoTrabalho(estacaoTrabalho);

            discoAtivoRepository.save(disco);

            for (DiscoParticaoDTO dpd : d.getParticoes()) {
                DiscoParticaoEntity dpe = new DiscoParticaoEntity();
                dpe.setCapacidade(dpd.getCapacidade());
                dpe.setUsado(dpd.getUsado());
                dpe.setPontoMontagem(dpd.getPontoMontagem());
                dpe.setDisco(disco);

                discoAtivoParticaoRepository.save(dpe);
            }
        }

        estacaoTrabalho = estacaoTrabalhoRepository.save(estacaoTrabalho);
        return new EstacaoTrabalhoSynchronizeDTO(estacaoTrabalho);
    }

    public void deleteWorkstation(Long id) {
        try {
            estacaoTrabalhoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Estação id: " + id + " não encontrada.");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Violação da integridade do banco de dados");
        }
    }


    public void synchronizeWorstationTeste(String ipAdrress) {
        try {
            InterfaceEntity byEnderecoIp = this.interfaceAtivoRepository.findByEnderecoIp(ipAdrress);

            System.out.println(byEnderecoIp.getEnderecoMac());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
