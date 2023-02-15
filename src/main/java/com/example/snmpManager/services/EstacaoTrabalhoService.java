package com.example.snmpManager.services;

import com.example.snmpManager.dto.DiscoDTO.DiscoDTO;
import com.example.snmpManager.dto.DiscoParticaoDTO.DiscoParticaoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoBasicDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoDTO;
import com.example.snmpManager.dto.EstacaoTrabalhoDTO.EstacaoTrabalhoSynchronizeDTO;
import com.example.snmpManager.dto.InterfaceAtivoDTO.InterfaceAtivoDTO;
import com.example.snmpManager.entities.DiscoEntity;
import com.example.snmpManager.entities.DiscoParticaoEntity;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.exceptions.InvalidAddressExecption;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.mibs.EstacaoTrabalhoMIB;
import com.example.snmpManager.objects.EstacaoTrabalho.WindowsObject;
import com.example.snmpManager.repositories.DiscoParticaoRepository;
import com.example.snmpManager.repositories.DiscoRepository;
import com.example.snmpManager.repositories.InterfaceRepository;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import com.example.snmpManager.util.AddressValidation;
import org.snmp4j.smi.OID;
import org.springframework.beans.factory.annotation.Autowired;
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
    private InterfaceRepository interfaceAtivoRepository;

    @Autowired
    private DiscoRepository discoAtivoRepository;

    @Autowired
    private DiscoParticaoRepository discoAtivoParticaoRepository;

    @Transactional
    public List<EstacaoTrabalhoBasicDTO> findAll() {
        List<EstacaoTrabalhoEntity> estacoes = estacaoTrabalhoRepository.findAll();
        return estacoes.stream().map(EstacaoTrabalhoBasicDTO::new).collect(Collectors.toList());
    }


    public WindowsObject getObjectData(String address) {

        if(!AddressValidation.isValidIpv4(address))
            throw new InvalidAddressExecption("Endereço ip [" + address + "] inválido!");

        SNMPRequestClient client = new SNMPRequestClient();

        client.start("udp:" + address + "/161", "public");

        EstacaoTrabalhoMIB mib = new EstacaoTrabalhoMIB();
        WindowsObject windowsObject = new WindowsObject();

        String sistemaOperacional = client.getAsString(new OID(mib.getSO_OID()));
        String arquitetura = client.getAsString(new OID(mib.getARQUITETURA_SO_OID()));
        String fabricante = client.getAsString(new OID(mib.getFABRICANTE_OID()));
        String modelo = client.getAsString(new OID(mib.getMODELO_OID()));
        String numeroSerie = client.getAsString(new OID(mib.getNUMERO_SERIE_OID()));
        String processador = client.getAsString(new OID(mib.getPROCESSADOR_OID()));
        String memoriaRam = client.getAsString(new OID(mib.getMEMORIA_RAM_OID()));
        String nomeMaquina = client.getAsString(new OID(mib.getNOME_OID()));
        String dominio = client.getAsString(new OID(mib.getDOMINIO_OID()));
        String usuarioLogado = client.getAsString(new OID(mib.getUSUARIO_LOGADO_OID()));
        String gateway = client.getAsString(new OID(mib.getGATEWAY_OID()));
        String dns = client.getAsString(new OID(mib.getDNS_OID()));
        String interfaces = client.getAsString(new OID(mib.getINTERFACES_OID()));
        String discosRigidos = client.getAsString(new OID(mib.getDISCO_RIGIDO_OID()));
        String impressoras = client.getAsString(new OID(mib.getIMPRESSORAS_OID()));

        windowsObject.setSistemaOperacional(sistemaOperacional);
        windowsObject.setArquiteturaSo(arquitetura);
        windowsObject.setFabricante(fabricante);
        windowsObject.setModelo(modelo);
        windowsObject.setNumeroSerie(numeroSerie);
        windowsObject.setProcessador(processador);
        windowsObject.setMemoriaRam(memoriaRam);
        windowsObject.setNomeHost(nomeMaquina);
        windowsObject.setDominio(dominio);
        windowsObject.setUltimoUsuarioLogado(usuarioLogado);
        windowsObject.setGateway(gateway);
        windowsObject.setDnsList(dns);
        windowsObject.addInterfaces(interfaces);
        windowsObject.addHardDisk(discosRigidos);
        windowsObject.addPrinters(impressoras);

        client.close();

        return windowsObject;
    }

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

            interfaceAtivoRepository.save(inter);
        }

        for (DiscoDTO d : dto.getDiscos()) {
            DiscoEntity disco = new DiscoEntity();
            disco.setNome(d.getNome());
            disco.setModelo(d.getModelo());
            disco.setNumeroSerie(d.getNumeroSerie());
            disco.setCapacidade(d.getCapacidade());
            disco.setEstacaoTrabalho(estacao);

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

        return new EstacaoTrabalhoDTO(estacao);
    }

    @Transactional
    public void synchronize(Long idAtivo) {

        Optional<EstacaoTrabalhoEntity> opt = estacaoTrabalhoRepository.findById(idAtivo);
        EstacaoTrabalhoEntity estacaoTrabalho = opt.orElseThrow(() -> new ResourceNotFoundException("Estação id: " + idAtivo + " não encontrada."));

        WindowsObject objAgent = new WindowsObject();

        for(InterfaceEntity i : estacaoTrabalho.getInterfaces()) {
            if(i.getEnderecoIp() != "" && i.getEnderecoIp() != null) {
                WindowsObject obj = getObjectData(i.getEnderecoIp());
                if (obj.getFabricante() != null) {
                    objAgent = obj;
                }
            }
        }

        if(objAgent.getFabricante() == null) {
            throw new ResourceNotFoundException("Nenhum endereco IPV4 definido para estação Id: " + idAtivo);
        }

        EstacaoTrabalhoSynchronizeDTO dto = new EstacaoTrabalhoSynchronizeDTO(objAgent);
        updateWorkStation(idAtivo, dto);
    }

    //atualiza estação informando id do ativo e dto
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

        if (!interfaces.isEmpty() && !discos.isEmpty()){
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

}
