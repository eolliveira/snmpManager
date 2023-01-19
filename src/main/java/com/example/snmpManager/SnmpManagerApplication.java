package com.example.snmpManager;

import java.io.IOException;

import java.lang.String;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.CommunityTarget;
import org.snmp4j.MessageDispatcher;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.Priv3DES;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.TransportIpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.snmpManager.services.SNMPService;
import com.example.snmpManager.services.TrapReceiver;

@SpringBootApplication
public class SnmpManagerApplication implements CommandLineRunner   {
	
	private Snmp snmp = null;
	private String address = null;
	
	@Autowired
	private SNMPService snmpService;
	
	public SnmpManagerApplication() {}
	
	public SnmpManagerApplication(String address) {
		this.address = address;
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SnmpManagerApplication.class, args);
		
		SnmpManagerApplication client = new SnmpManagerApplication("udp:localhost/161");
		client.start();
		
		String sysObjID = client.getAsString(new OID(".1.3.6.1.2.1.1.2.0")); //OID
		String sysDescr = client.getAsString(new OID(".1.3.6.1.2.1.1.1.0")); //Hardware
		String SysName = client.getAsString(new OID(".1.3.6.1.2.1.1.5.0")); //hostname
		String SysUpTime = client.getAsString(new OID(".1.3.6.1.2.1.25.1.1.0")); //tempo ligado
		String ipAdEntAddr = client.getAsString(new OID(".1.3.6.1.2.1.4.20.1.1.10.0.5.237")); //tempo ligado
		String hrMemorySize = client.getAsString(new OID(".1.3.6.1.2.1.25.2.2.0")); //memoria ram intalada
		String mac = client.getAsString(new OID(".1.3.6.1.2.1.2.2.1.6.20")); //memoria ram intalada
		String  hrSystemNumUsers = client.getAsString(new OID(".1.3.6.1.2.1.25.1.5.0")); //numero de usuários



		System.out.println("OID:" + sysObjID);
		System.out.println(sysDescr);
		System.out.println("Hostname: " + SysName);
		System.out.println("Tempo ligado: " + SysUpTime);
		System.out.println("Memória Ram: " + hrMemorySize);
		System.out.println("MAC: " + mac);
		System.out.println("Nr Usuários: " +  hrSystemNumUsers);
		System.out.println(" ---------------------------------");
    	
//		TrapReceiver trapReceiver = new TrapReceiver();
//		
//        	try {
//				trapReceiver.listen(new UdpAddress("localhost/162"));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}	

	/**
	 * Inicie a sessão Snmp. Se você esquecer o método listen(), 
	 * não obterá nenhuma resposta porque a comunicação é assíncrona
	 *  e o método listen() aguarda as respostas.
	 * 
	 * @throws IOException
	 * 
	 */
	private void start() throws IOException {

		TransportMapping transport = new DefaultUdpTransportMapping();
		snmp = new Snmp(transport);

		// Não se esqueça desta linha!
		transport.listen();
	}

	
	
	/**
	 * 63 Método que pega um único OID e retorna a resposta do agente como uma String. 64
	 * 
	 * @param oid
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	public String getAsString(OID oid) throws IOException {
		ResponseEvent event = get(new OID[] { oid });
		return event.getResponse().get(0).getVariable().toString();
	}


	
	
	 // Este método é capaz de lidar com vários OIDs
	public ResponseEvent get(OID oids[]) throws IOException {

		PDU pdu = new PDU();

		for (OID oid : oids) {
			pdu.add(new VariableBinding(oid));
		}

		pdu.setType(PDU.GET);

		ResponseEvent event = snmp.send(pdu, getTarget(), null);

		if (event != null) {
			return event;
		}

		throw new RuntimeException("GET timed out");

	}

	
	/**
	 * Este método retorna um Target, que contém informações sobre
	 * onde os dados devem ser buscados e como
	 */
	private Target getTarget() {

		Address targetAddress = GenericAddress.parse(address);
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString("public"));
		target.setAddress(targetAddress);
		target.setRetries(2);
		target.setTimeout(1500);
		target.setVersion(SnmpConstants.version2c);
		return target;
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		
		//traz agentes configurados
		//snmpService.discoverButtonActionPerformed();
		
		
	}


	
}
