package com.example.snmpManager;

import com.example.snmpManager.services.SNMPRequestClient;
import com.example.snmpManager.services.SNMPTrapReciever;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.UdpAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SnmpManagerApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(SnmpManagerApplication.class, args);


		//inicia ouvinte de informaçoes na porta 161 do client informado
		SNMPRequestClient client = new SNMPRequestClient();
		client.start("udp:localhost/161", "public");


		String sysObjID = client.getAsString(new OID(".1.3.6.1.2.1.1.2.0")); //OID
		String sysDescr = client.getAsString(new OID(".1.3.6.1.2.1.1.1.0")); //Hardware
		String SysName = client.getAsString(new OID(".1.3.6.1.2.1.1.5.0")); //hostname
		String SysUpTime = client.getAsString(new OID(".1.3.6.1.2.1.25.1.1.0")); //tempo ligado
		String ipAdEntAddr = client.getAsString(new OID(".1.3.6.1.2.1.4.20.1.1.10.0.5.237")); //tempo ligado
		String hrMemorySize = client.getAsString(new OID(".1.3.6.1.2.1.25.2.2.0")); //memoria ram intalada
		String mac = client.getAsString(new OID(".1.3.6.1.2.1.55.1.5.1.8.8")); //memoria ram intalada
		String  hrSystemNumUsers = client.getAsString(new OID(".1.3.6.1.2.1.25.1.5.0")); //numero de usuários

		System.out.println("OID:" + sysObjID);
		System.out.println(sysDescr);
		System.out.println("Hostname: " + SysName);
		System.out.println("Tempo ligado: " + SysUpTime);
		System.out.println("Memória Ram: " + hrMemorySize);
		System.out.println("MAC: " + mac);
		System.out.println("Nr Usuários: " +  hrSystemNumUsers);
		System.out.println(" ---------------------------------");

		//inicia ouvinte de trap(armadilhas) dos agentes
		SNMPTrapReciever trapReciever = new SNMPTrapReciever();
		trapReciever.listen(new UdpAddress("localhost/162"));


	}


}
