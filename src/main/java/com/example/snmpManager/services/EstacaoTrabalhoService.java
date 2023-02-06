package com.example.snmpManager.services;

import com.example.snmpManager.dto.*;
import com.example.snmpManager.entities.AtivoDiscoEntity;
import com.example.snmpManager.entities.AtivoDiscoParticaoEntity;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.entities.AtivoInterfaceEntity;
import com.example.snmpManager.exceptions.InvalidAddressExecption;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.mibs.WindowsMIB;
import com.example.snmpManager.objects.WindowsObject;
import com.example.snmpManager.repositories.DiscoAtivoParticaoRepository;
import com.example.snmpManager.repositories.DiscoAtivoRepository;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import com.example.snmpManager.repositories.InterfaceAtivoRepository;
import com.example.snmpManager.util.AddressValidation;
import org.snmp4j.smi.OID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EstacaoTrabalhoService {

    @Autowired
    private EstacaoTrabalhoRepository estacaoTrabalhoRepository;

    @Autowired
    private InterfaceAtivoRepository interfaceAtivoRepository;

    @Autowired
    private DiscoAtivoRepository discoAtivoRepository;

    @Autowired
    private DiscoAtivoParticaoRepository discoAtivoParticaoRepository;

    //busca inf windows
    public WindowsObject getObjectData(String address) {

        if(!AddressValidation.isValidIpv4(address))
            throw new InvalidAddressExecption("Endereço ip [" + address + "] inválido!");

        SNMPRequestClient client = new SNMPRequestClient();

        client.start("udp:" + address + "/161", "public");

        WindowsMIB mib = new WindowsMIB();
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
        String usuarioLogado = client.getAsString(new OID(mib.getUSUARIO_LOGADO()));
        String gateway = client.getAsString(new OID(mib.getGATEWAY_OID()));
        String dns = client.getAsString(new OID(mib.getDNS_OID()));
        String interfaces = client.getAsString(new OID(mib.getINTERFACES_OID()));
        String discosRigidos = client.getAsString(new OID(mib.getDISCO_RIGIDO()));

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

        return windowsObject;
    }

    //salva nova estação de trabalho
    @Transactional
    public EstacaoTrabalhoDTO insertNewWorkStation(EstacaoTrabalhoDTO dto) {

        EstacaoTrabalhoEntity estacao = new EstacaoTrabalhoEntity(dto);

        estacao = estacaoTrabalhoRepository.save(estacao);

        for (InterfaceAtivoDTO i : dto.getInterfaces()) {
            AtivoInterfaceEntity inter = new AtivoInterfaceEntity();
            inter.setNomeLocal(i.getNomeLocal());
            inter.setFabricante(i.getFabricante());
            inter.setEnderecoMac(i.getEnderecoMac());
            inter.setEnderecoIp(i.getEnderecoIp());
            inter.setMascaraSubRede(i.getMascaraSubRede());
            inter.setEstacaoTrabalho(estacao);

            interfaceAtivoRepository.save(inter);
        }

        for (AtivoDiscoDTO d : dto.getDiscos()) {
            AtivoDiscoEntity disco = new AtivoDiscoEntity();
            disco.setNome(d.getNome());
            disco.setModelo(d.getModelo());
            disco.setNumeroSerie(d.getNumeroSerie());
            disco.setCapacidade(d.getCapacidade());
            disco.setUsado(d.getUsado());
            disco.setDisponivel(d.getDisponivel());
            disco.setEstacaoTrabalho(estacao);

            discoAtivoRepository.save(disco);

            for (AtivoDiscoParticaoDTO dpd : d.getParticoes()) {
                AtivoDiscoParticaoEntity dpe = new AtivoDiscoParticaoEntity();
                dpe.setCapacidade(dpd.getCapacidade());
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

        for(AtivoInterfaceEntity i : estacaoTrabalho.getInterfaces()) {
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

        EstacaoTrabalhoUpdateDTO dto = new EstacaoTrabalhoUpdateDTO(objAgent);
        updateWorkStation(idAtivo, dto);
    }

    //atualiza estação informando id do ativo e dto
    @Transactional
    public EstacaoTrabalhoUpdateDTO updateWorkStation(Long idAtivo, EstacaoTrabalhoUpdateDTO dto) {

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
        List<AtivoInterfaceEntity> interfaces = interfaceAtivoRepository.findAllByEstacaoTrabalho_Id(estacaoTrabalho.getId());
        List<AtivoDiscoEntity> discos = discoAtivoRepository.findAllByEstacaoTrabalho_Id(estacaoTrabalho.getId());

        if (!interfaces.isEmpty() && !discos.isEmpty()){
            //remove tudo()
            interfaceAtivoRepository.deleteAll(interfaces);
            discoAtivoRepository.deleteAll(discos);
        }

        //adiciona interfaces atualizadas
        for (InterfaceAtivoDTO i : dto.getInterfaces()) {
            AtivoInterfaceEntity inter = new AtivoInterfaceEntity();
            inter.setNomeLocal(i.getNomeLocal());
            inter.setFabricante(i.getFabricante());
            inter.setEnderecoMac(i.getEnderecoMac());
            inter.setEnderecoIp(i.getEnderecoIp());
            inter.setMascaraSubRede(i.getMascaraSubRede());
            inter.setEstacaoTrabalho(estacaoTrabalho);
            interfaceAtivoRepository.save(inter);
        }

        //adiciona discos atualizados
        for (AtivoDiscoDTO d : dto.getDiscos()) {
            AtivoDiscoEntity disco = new AtivoDiscoEntity();
            disco.setNome(d.getNome());
            disco.setModelo(d.getModelo());
            disco.setNumeroSerie(d.getNumeroSerie());
            disco.setCapacidade(d.getCapacidade());
            disco.setUsado(d.getUsado());
            disco.setDisponivel(d.getDisponivel());
            disco.setEstacaoTrabalho(estacaoTrabalho);

            discoAtivoRepository.save(disco);

            for (AtivoDiscoParticaoDTO dpd : d.getParticoes()) {
                AtivoDiscoParticaoEntity dpe = new AtivoDiscoParticaoEntity();
                dpe.setCapacidade(dpd.getCapacidade());
                dpe.setPontoMontagem(dpd.getPontoMontagem());
                dpe.setDisco(disco);

                discoAtivoParticaoRepository.save(dpe);
            }
        }

        estacaoTrabalho = estacaoTrabalhoRepository.save(estacaoTrabalho);
        return new EstacaoTrabalhoUpdateDTO(estacaoTrabalho);
    }

}
