package com.example.snmpManager.services;

import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.objects.TrapObject;
import com.example.snmpManager.repositories.InterfaceRepository;
import com.example.snmpManager.services.SyncService.SyncService;
import org.snmp4j.*;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.security.Priv3DES;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.TransportIpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.AbstractTransportMapping;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SNMPTrapReciever implements CommandResponder {
    @Autowired
    private SyncService syncService;


    @Autowired
    private InterfaceRepository interfaceRepository;

    public synchronized void listen(TransportIpAddress address)
            throws IOException {
        AbstractTransportMapping transport;
        if (address instanceof TcpAddress) {
            transport = new DefaultTcpTransportMapping((TcpAddress) address);
        } else {
            transport = new DefaultUdpTransportMapping((UdpAddress) address);
        }

        ThreadPool threadPool = ThreadPool.create("DispatcherPool", 10);
        MessageDispatcher mDispathcher = new MultiThreadedMessageDispatcher(
                threadPool, new MessageDispatcherImpl());

        // add message processing models
        mDispathcher.addMessageProcessingModel(new MPv1());
        mDispathcher.addMessageProcessingModel(new MPv2c());

        // adiciona protocolos de segurança
        SecurityProtocols.getInstance().addDefaultProtocols();
        SecurityProtocols.getInstance().addPrivacyProtocol(new Priv3DES());

        // Cria destino()
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));

        Snmp snmp = new Snmp(mDispathcher, transport);
        snmp.addCommandResponder(this);

        transport.listen();
        System.out.println("Listening on " + address);

        try {
            this.wait();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

     //Este método será chamado sempre que um pdu for recebido na porta especificada no método listen()
    public synchronized void processPdu(CommandResponderEvent cmdRespEvent) {
        System.out.println("PDU Recebido...");
        PDU pdu = cmdRespEvent.getPDU();
        if (pdu != null) {
            System.out.println("Tipo da armadilha = " + pdu.getType());
            System.out.println("Variaveis = " + pdu.getVariableBindings());


            //RECEBE AS INFORMAÇÕES DO AGENTE QUE SOLICITA O SISNCRONISMO
            String descricao = pdu.get(0).getVariable().toString(); // descrição solictação
            String tipoAtivo = pdu.get(1).getVariable().toString(); //tipo dispositivo
            String ipAddress = pdu.get(2).getVariable().toString(); // ip
            String instante = pdu.get(3).getVariable().toString(); // instante requisição

            TrapObject trapObject = new TrapObject(descricao, tipoAtivo, ipAddress, instante);

            syncService.checkAgentSync(trapObject);
            InterfaceEntity entity = interfaceRepository.findByEnderecoIp(ipAddress);
            System.out.println(entity.getEnderecoMac());
        }
    }
}
