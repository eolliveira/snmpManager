package com.example.snmpManager.services;

import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SNMPRequestClient {

    //verificar (vazamento de memoria)
    private Snmp snmp;
    private String address;
    private String community;

    public void start(String address, String commmunity) throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);

        this.address = address;
        this.community = commmunity;

        // Não se esqueça desta linha!
        transport.listen();
    }

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

        ResponseEvent event = snmp.send(pdu, getTarget(address, community), null);

        if (event != null) {
            return event;
        }

        throw new RuntimeException("GET timed out");
    }


    /**
     * Este método retorna um Target, que contém informações sobre
     * onde os dados devem ser buscados e como
     */
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
