package com.example.snmpManager.services;

import com.example.snmpManager.dto.*;
import com.example.snmpManager.entities.DiscoAtivoEntity;
import com.example.snmpManager.entities.DiscoAtivoParticaoEntity;
import com.example.snmpManager.entities.EstacaoTrabalhoEntity;
import com.example.snmpManager.entities.InterfaceAtivoEntity;
import com.example.snmpManager.mibs.WindowsMIB;
import com.example.snmpManager.objects.WindowsObject;
import com.example.snmpManager.repositories.DiscoAtivoParticaoRepository;
import com.example.snmpManager.repositories.DiscoAtivoRepository;
import com.example.snmpManager.repositories.EstacaoTrabalhoRepository;
import com.example.snmpManager.repositories.InterfaceAtivoRepository;
import org.snmp4j.smi.OID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

        SNMPRequestClient client = new SNMPRequestClient();
        try {
            client.start("udp:" + address + "/161", "public");
        } catch (IOException e) {
            throw new RuntimeException("Não foi possivel inicar serviço na porta 161: " + e.getMessage());
        }

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

        EstacaoTrabalhoEntity estacao = new EstacaoTrabalhoEntity();
        estacao.setFabricante(dto.getFabricante());
        estacao.setNumeroSerie(dto.getNumeroSerie());
        estacao.setModelo(dto.getModelo());
        estacao.setGateway(dto.getGateway());
        estacao.setDnsList(dto.getDnsList());
        estacao.setSistemaOperacional(dto.getSistemaOperacional());
        estacao.setArquiteturaSo(dto.getArquiteturaSo());
        estacao.setProcessador(dto.getProcessador());
        estacao.setMemoriaRam(dto.getMemoriaRam());
        estacao.setNomeHost(dto.getNomeHost());
        estacao.setDominio(dto.getDominio());
        estacao.setUltimoUsuarioLogado(dto.getUltimoUsuarioLogado());

        estacao = estacaoTrabalhoRepository.save(estacao);

        for (InterfaceAtivoDTO i : dto.getInterfaces()) {
            InterfaceAtivoEntity inter = new InterfaceAtivoEntity();
            inter.setNomeLocal(i.getNomeLocal());
            inter.setFabricante(i.getFabricante());
            inter.setEnderecoMac(i.getEnderecoMac());
            inter.setEnderecoIp(i.getEnderecoIp());
            inter.setMascaraSubRede(i.getMascaraSubRede());
            inter.setEstacaoTrabalho(estacao);

            interfaceAtivoRepository.save(inter);
        }

        for (DiscoAtivoDTO d : dto.getDiscos()) {
            DiscoAtivoEntity disco = new DiscoAtivoEntity();
            disco.setNome(d.getNome());
            disco.setModelo(d.getModelo());
            disco.setNumeroSerie(d.getNumeroSerie());
            disco.setCapacidade(d.getCapacidade());
            disco.setUsado(d.getUsado());
            disco.setDisponivel(d.getDisponivel());
            disco.setEstacaoTrabalho(estacao);

            discoAtivoRepository.save(disco);

            for (DiscoAtivoParticaoDTO dpd : d.getParticoes()) {
                DiscoAtivoParticaoEntity dpe = new DiscoAtivoParticaoEntity();
                dpe.setCapacidade(dpd.getCapacidade());
                dpe.setPontoMontagem(dpd.getPontoMontagem());
                dpe.setDisco(disco);

                discoAtivoParticaoRepository.save(dpe);
            }
        }

        return new EstacaoTrabalhoDTO(estacao);
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
        List<InterfaceAtivoEntity> interfaces = interfaceAtivoRepository.findAllByEstacaoTrabalho_Id(estacaoTrabalho.getId());
        List<DiscoAtivoEntity> discos = discoAtivoRepository.findAllByEstacaoTrabalho_Id(estacaoTrabalho.getId());

        //remove tudo
        interfaceAtivoRepository.deleteAll(interfaces);
        discoAtivoRepository.deleteAll(discos);

        //adiciona interfaces atualizadas
        for (InterfaceAtivoDTO i : dto.getInterfaces()) {
            InterfaceAtivoEntity inter = new InterfaceAtivoEntity();
            inter.setNomeLocal(i.getNomeLocal());
            inter.setFabricante(i.getFabricante());
            inter.setEnderecoMac(i.getEnderecoMac());
            inter.setEnderecoIp(i.getEnderecoIp());
            inter.setMascaraSubRede(i.getMascaraSubRede());
            inter.setEstacaoTrabalho(estacaoTrabalho);
            interfaceAtivoRepository.save(inter);
        }

        //adiciona discos atualizados
        for (DiscoAtivoDTO d : dto.getDiscos()) {
            DiscoAtivoEntity disco = new DiscoAtivoEntity();
            disco.setNome(d.getNome());
            disco.setModelo(d.getModelo());
            disco.setNumeroSerie(d.getNumeroSerie());
            disco.setCapacidade(d.getCapacidade());
            disco.setUsado(d.getUsado());
            disco.setDisponivel(d.getDisponivel());
            disco.setEstacaoTrabalho(estacaoTrabalho);

            discoAtivoRepository.save(disco);

            for (DiscoAtivoParticaoDTO dpd : d.getParticoes()) {
                DiscoAtivoParticaoEntity dpe = new DiscoAtivoParticaoEntity();
                dpe.setCapacidade(dpd.getCapacidade());
                dpe.setPontoMontagem(dpd.getPontoMontagem());
                dpe.setDisco(disco);

                discoAtivoParticaoRepository.save(dpe);
            }
        }

        estacaoTrabalho = estacaoTrabalhoRepository.save(estacaoTrabalho);
        return new EstacaoTrabalhoUpdateDTO(estacaoTrabalho);
    }

    @Transactional
    public void synchronize(Long idActive) {

        //obtem ip
        EstacaoTrabalhoEntity estacaoTrabalho = estacaoTrabalhoRepository.getReferenceById(idActive);

        WindowsObject objAgent = new WindowsObject();

        for(InterfaceAtivoEntity i : estacaoTrabalho.getInterfaces()) {
            if(i.getEnderecoIp() != "") {
                //busca info windows
                //pode lançar exception ip  nn encontrado
                WindowsObject obj = getObjectData(i.getEnderecoIp());
                if (obj.getFabricante() != null) {
                    objAgent = obj;
                }
            }

//            if (objAgent.getFabricante() != null){
//                return;
//            }

        }

        //copia dto
        EstacaoTrabalhoUpdateDTO dto = new EstacaoTrabalhoUpdateDTO();
        dto.setFabricante(objAgent.getFabricante());
        dto.setNumeroSerie(objAgent.getNumeroSerie());
        dto.setModelo(objAgent.getModelo());
        dto.setSistemaOperacional(objAgent.getSistemaOperacional());
        dto.setProcessador(objAgent.getProcessador());
        dto.setArquiteturaSo(objAgent.getArquiteturaSo());
        dto.setMemoriaRam(objAgent.getMemoriaRam());
        dto.setNomeHost(objAgent.getNomeHost());
        dto.setUltimoUsuarioLogado(objAgent.getUltimoUsuarioLogado());
        dto.setDominio(objAgent.getDominio());
        dto.setDnsList(objAgent.getDnsList());
        dto.setGateway(objAgent.getGateway());

        objAgent.getInterfaces().stream().map(i -> dto.getInterfaces().add(new InterfaceAtivoDTO(i))).collect(Collectors.toList());
        objAgent.getDiscos().stream().map(d -> dto.getDiscos().add(new DiscoAtivoDTO(d))).collect(Collectors.toList());

        //update

        updateWorkStation(idActive, dto);

    }

}
