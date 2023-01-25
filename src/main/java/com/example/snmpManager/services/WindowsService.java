package com.example.snmpManager.services;

import com.example.snmpManager.objects.WindowsObject;
import org.snmp4j.smi.OID;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WindowsService {

    //busca inf windows
    public WindowsObject getObjectData(String address) throws IOException {

        SNMPRequestClient client = new SNMPRequestClient();
        client.start("udp:" + address + "/161", "public");

        String baseOID = ".1.3.6.1.4.1.12345";

        String sistemaOperacional = client.getAsString(new OID(baseOID + ".1.1.0"));
        String arquitetura = client.getAsString(new OID(baseOID + ".1.2.0"));
        String fabricante = client.getAsString(new OID(baseOID + ".1.3.0"));
        String modelo = client.getAsString(new OID(baseOID + ".1.4.0"));
        String numeroSerie = client.getAsString(new OID(baseOID + ".1.5.0"));
        String processador = client.getAsString(new OID(baseOID + ".1.6.0"));
        String memoriaRam = client.getAsString(new OID(baseOID + ".2.1.0"));
        String nomeMaquina = client.getAsString(new OID(baseOID + ".2.2.1.0"));
        String dominio = client.getAsString(new OID(baseOID + ".2.2.2.0"));
        String gateway = client.getAsString(new OID(baseOID + ".2.2.3.0"));
        String dns = client.getAsString(new OID(baseOID + ".2.2.4.0"));
        String interfaces = client.getAsString(new OID(baseOID + ".2.2.6.0"));


        WindowsObject win = new WindowsObject();

        win.setOs(sistemaOperacional);
        win.setOsArchitecture(arquitetura);
        win.setManufacturer(fabricante);
        win.setSerialNumber(numeroSerie);
        win.setProcessor(processador);
        win.setRamMemory(memoriaRam);
        win.setHostname(nomeMaquina);
        win.setDomain(dominio);
        win.setGateway(gateway);
        win.setModel(modelo);
        win.setDns(dns);
        win.addInterfaces(interfaces);

        return win;
    }


}
