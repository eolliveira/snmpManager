package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.exceptions.InvalidAddressExecption;
import com.example.snmpManager.mibs.EstacaoTrabalhoMIB;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
import com.example.snmpManager.services.SNMPRequestClient;
import com.example.snmpManager.util.AddressValidation;
import org.snmp4j.smi.OID;
import org.springframework.stereotype.Component;

@Component
public class WorkstationDataService {

    public WorkstationObject getWorkstationData(String address) {
        if (!AddressValidation.isValidIpv4(address))
            throw new InvalidAddressExecption("Endereço ip [" + address + "] inválido!");

        SNMPRequestClient client = new SNMPRequestClient();

        client.start("udp:" + address + "/161", "public");

        EstacaoTrabalhoMIB mib = new EstacaoTrabalhoMIB();
        WorkstationObject windowsObject = new WorkstationObject();

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
        String placasVideo = client.getAsString(new OID(mib.getPLACAS_VIDEO_OID()));
        String programasInstalados = client.getAsString(new OID(mib.getPROGRAMAS_OID()));

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
        windowsObject.addVideoCards(placasVideo);
        windowsObject.addSoftware(programasInstalados);

        client.close();

        return windowsObject;
    }
}
