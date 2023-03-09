package com.example.snmpManager.services.EstacaoTrabalhoService;

import com.example.snmpManager.exceptions.ClientConnectionFailedExecption;
import com.example.snmpManager.exceptions.InvalidAddressExecption;
import com.example.snmpManager.exceptions.UnableToGetDeviceDataException;
import com.example.snmpManager.mibs.EstacaoTrabalhoMIB;
import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
import com.example.snmpManager.services.SnmpService.SNMPRequestClient;
import com.example.snmpManager.util.AddressValidation;
import org.snmp4j.smi.OID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDataFromWorkstationService {

    @Autowired
    private SNMPRequestClient snmpRequestClient;

    public WorkstationObject getWorkstationData(String address) {
        if (!AddressValidation.isValidIpv4(address))
            throw new InvalidAddressExecption("Endereço ip [" + address + "] inválido!");

        EstacaoTrabalhoMIB mib = new EstacaoTrabalhoMIB();
        WorkstationObject windowsObject = new WorkstationObject();

        try {
            snmpRequestClient.start(address, "public", "/1061");

            String sistemaOperacional = snmpRequestClient.getAsString(new OID(mib.getSO_OID()));
            String arquitetura = snmpRequestClient.getAsString(new OID(mib.getARQUITETURA_SO_OID()));
            String fabricante = snmpRequestClient.getAsString(new OID(mib.getFABRICANTE_OID()));
            String modelo = snmpRequestClient.getAsString(new OID(mib.getMODELO_OID()));
            String numeroSerie = snmpRequestClient.getAsString(new OID(mib.getNUMERO_SERIE_OID()));
            String processador = snmpRequestClient.getAsString(new OID(mib.getPROCESSADOR_OID()));
            String memoriaRam = snmpRequestClient.getAsString(new OID(mib.getMEMORIA_RAM_OID()));
            String tempoLigado = snmpRequestClient.getAsString(new OID(mib.getTEMPO_LIGADO_OID()));
            String nomeMaquina = snmpRequestClient.getAsString(new OID(mib.getNOME_OID()));
            String dominio = snmpRequestClient.getAsString(new OID(mib.getDOMINIO_OID()));
            String usuarioLogado = snmpRequestClient.getAsString(new OID(mib.getUSUARIO_LOGADO_OID()));
            String gateway = snmpRequestClient.getAsString(new OID(mib.getGATEWAY_OID()));
            String dns = snmpRequestClient.getAsString(new OID(mib.getDNS_OID()));
            String interfaces = snmpRequestClient.getAsString(new OID(mib.getINTERFACES_OID()));
            String discosRigidos = snmpRequestClient.getAsString(new OID(mib.getDISCO_RIGIDO_OID()));
            String impressoras = snmpRequestClient.getAsString(new OID(mib.getIMPRESSORAS_OID()));
            String placasVideo = snmpRequestClient.getAsString(new OID(mib.getPLACAS_VIDEO_OID()));
            String programasInstalados = snmpRequestClient.getAsString(new OID(mib.getPROGRAMAS_OID()));

            windowsObject.setSistemaOperacional(sistemaOperacional);
            windowsObject.setArquiteturaSo(arquitetura);
            windowsObject.setFabricante(fabricante);
            windowsObject.setModelo(modelo);
            windowsObject.setNumeroSerie(numeroSerie);
            windowsObject.setProcessador(processador);
            windowsObject.setMemoriaRam(memoriaRam);
            windowsObject.setTempoLigado(tempoLigado);
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
        } catch (ClientConnectionFailedExecption e) {
            throw new UnableToGetDeviceDataException("Falha ao estabelecer conexão com o agente, pelo endereço ip [" + address + "]: " + e.getMessage());
        } finally {
            snmpRequestClient.close();
        }

        return windowsObject;
    }
}
