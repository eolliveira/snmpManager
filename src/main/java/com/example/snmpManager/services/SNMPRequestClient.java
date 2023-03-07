package com.example.snmpManager.services;

import com.example.snmpManager.exceptions.ClientConnectionFailedExecption;
import com.example.snmpManager.exceptions.FailureInitializeUdpTransport;
import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SNMPRequestClient {

    private Snmp snmp;
    private String address;
    private String community;
    private TransportMapping transport;

    public void start(String address, String commmunity)  {
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            this.address = "udp:" + address + "/161";
            this.community = commmunity;

            transport.listen();

        } catch (IOException e) {
            throw new FailureInitializeUdpTransport("Falha ao inicializar o mapeamento de transporte UDP: " + e.getMessage());
        }
    }

    public void close()  {
        try {
            transport.close();
            snmp.close();
        } catch (IOException e) {
            throw new FailureInitializeUdpTransport("Falha ao encerrar o mapeamento de transporte UDP: " + e.getMessage());
        }
    }

    public String getAsString(OID oid) {
        try {
            ResponseEvent event = get(new OID[]{oid});
            return event.getResponse().get(0).getVariable().toString();
        } catch (NullPointerException e) {
            throw new ClientConnectionFailedExecption("Não foi possivel obter informações do OID: " + oid);
        }
    }

    // Este método é capaz de lidar com vários OIDs
    public ResponseEvent get(OID oids[]) {

        PDU pdu = new PDU();

        for (OID oid : oids) {
            pdu.add(new VariableBinding(oid));
        }

        pdu.setType(PDU.GET);

        try {
            ResponseEvent event = snmp.send(pdu, getTarget(address, community), null);

            if (event != null) {
                return event;
            }

            throw new RuntimeException("GET timed out");
        } catch (IOException e) {
            throw new RuntimeException("Não foi possivel enviar/obter resposta do agente!");
        }

    }


    // Este método retorna um Target, que contém informações sobre
    // onde os dados devem ser buscados e como
    private Target getTarget(String address, String community) {
        Address targetAddress = GenericAddress.parse(address);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version2c);
        return target;
    }

}
