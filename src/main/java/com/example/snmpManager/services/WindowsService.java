package com.example.snmpManager.services;

import com.example.snmpManager.mibs.WindowsMIB;
import com.example.snmpManager.objects.WindowsObject;
import org.snmp4j.smi.OID;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WindowsService {

    //busca inf windows
    public WindowsObject getObjectData(String address) {

        SNMPRequestClient client = new SNMPRequestClient();
        try {
            client.start("udp:" + address + "/161", "public");
        } catch (IOException e) {
            throw new RuntimeException("Não foi possivel inicar serviço na porta 161 !");
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
        String gateway = client.getAsString(new OID(mib.getGATEWAY_OID()));
        String dns = client.getAsString(new OID(mib.getDNS_OID()));
        String interfaces = client.getAsString(new OID(mib.getINTERFACES_OID()));

        windowsObject.setOs(sistemaOperacional);
        windowsObject.setOsArchitecture(arquitetura);
        windowsObject.setManufacturer(fabricante);
        windowsObject.setModel(modelo);
        windowsObject.setSerialNumber(numeroSerie);
        windowsObject.setProcessor(processador);
        windowsObject.setRamMemory(memoriaRam);
        windowsObject.setHostname(nomeMaquina);
        windowsObject.setDomain(dominio);
        windowsObject.setGateway(gateway);
        windowsObject.setDns(dns);
        windowsObject.addInterfaces(interfaces);

        return windowsObject;
    }

}
