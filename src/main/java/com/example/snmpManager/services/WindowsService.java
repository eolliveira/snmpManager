package com.example.snmpManager.services;

import com.example.snmpManager.objects.WindowsObject;
import org.snmp4j.smi.OID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WindowsService {

    //busca inf windows
    public WindowsObject getInformation(String address) {

        SNMPRequestClient requestClient = new SNMPRequestClient();

        String x = ".1.3.6.1.4.1.12345";

        String sysObjID = client.getAsString(new OID(x + ".1.1.0")); //OID
        String sysDescr = client.getAsString(new OID(x + ".1.2.0")); //Hardware
        String SysName = client.getAsString(new OID(x + ".1.3.0")); //hostname
        String SysUpTime = client.getAsString(new OID(x + ".1.5.0")); //tempo ligado
        String ipAdEntAddr = client.getAsString(new OID(x + ".1.6.0")); //tempo ligado
        String ultimoUsuarioLogado = client.getAsString(new OID(x + "2.1.0")); //tempo ligado
        String hrMemorySize = client.getAsString(new OID(x + ".2.2.1.0")); //memoria ram intalada
        String mac = client.getAsString(new OID(x + ".2.2.2.0")); //memoria ram intalada
        String hrSystemNumUsers = client.getAsString(new OID(x + ".2.2.3.0")); //numero de usuários
        String OSarquiteture = client.getAsString(new OID(x + ".2.2.4.0")); //numero de usuários

        System.out.println("OID:" + sysObjID);
        System.out.println(sysDescr);
        System.out.println("Hostname: " + SysName);
        System.out.println("Tempo ligado: " + SysUpTime);
        System.out.println("Memória Ram: " + hrMemorySize);
        System.out.println("MAC: " + mac);
        System.out.println("Nr Usuários: " +  hrSystemNumUsers);
        System.out.println("Arquitetura SO: " +  OSarquiteture);
        System.out.println("Ultimo usuário logado: "  +ultimoUsuarioLogado);
        System.out.println(" ---------------------------------");






        return null;
    }


}
