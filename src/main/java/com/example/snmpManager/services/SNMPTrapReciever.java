package com.example.snmpManager.services;

import com.example.snmpManager.objects.TrapObject;
import com.example.snmpManager.services.EstacaoTrabalhoService.FindWorkstationService;
import com.example.snmpManager.services.EstacaoTrabalhoService.GetDataFromWorkstationService;
import com.example.snmpManager.services.SyncService.SyncService;
import org.snmp4j.*;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.TransportIpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SNMPTrapReciever implements CommandResponder {
    @Autowired
    FindWorkstationService findWorkstationService;

    @Autowired
    GetDataFromWorkstationService getDataFromWorkstationService;

    @Autowired
    SyncService syncService;

    public void listen(TransportIpAddress address) throws IOException, InterruptedException {
        // Create a UDP transport mapping
        System.out.println("testee" + address);
        TransportMapping transport = new DefaultUdpTransportMapping(new UdpAddress(address + "/162"));

        // Create a SNMP object
        Snmp snmp = new Snmp(transport);

        // Start the transport
        transport.listen();

        // Add the trap receiver
        snmp.addCommandResponder(this);

        System.out.println("Trap receiver started");

        // Wait for traps to arrive
        Thread.sleep(Long.MAX_VALUE);
    }

    //Este método será chamado sempre que um pdu for recebido na porta especificada no método listen()
    @Override
    public synchronized void processPdu(CommandResponderEvent event) {
        PDU pdu = event.getPDU();
        if (pdu != null && pdu.getType() == PDU.NOTIFICATION) {
            // Get the remote address of the trap sender
            Address address = event.getPeerAddress();
            System.out.println("Trap received from " + address + " / / Thread: " + Thread.currentThread().getName());

            // Get the community string used by the trap sender
            String community = new String(event.getSecurityName());

            // Get the variable bindings from the trap
            VariableBinding[] varBinds = pdu.toArray();

            // Print out the variable bindings
            for (VariableBinding varBind : varBinds) {
                System.out.println("Variable: " + varBind.getOid() + " = " + varBind.getVariable());
            }

            ///////////////////////////////////


            //RECEBE AS INFORMAÇÕES DO AGENTE QUE SOLICITA O SISNCRONISMO
            String descricao = pdu.get(0).getVariable().toString(); // descrição solictação
            String tipoAtivo = pdu.get(1).getVariable().toString(); //tipo dispositivo
            String ipAddress = pdu.get(2).getVariable().toString(); // ip
            String instante = pdu.get(3).getVariable().toString(); // instante requisição

            TrapObject trapObject = new TrapObject(descricao, tipoAtivo, ipAddress, instante);

            try {
                Thread.sleep(8000);  //aguardar thresdpool terminar de executar
                System.out.println("entrando no metodo TRAP --------------------------------------------------- thread  = " + Thread.currentThread().getName());
                syncService.checkAgentSync(trapObject);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }





        }
    }
//    @Override
//    public synchronized void processPdu(CommandResponderEvent cmdRespEvent) {
//
//        System.out.println("PDU Recebido...");
//        PDU pdu = cmdRespEvent.getPDU();
//        if (pdu != null) {
//            System.out.println("Tipo da armadilha = " + pdu.getType());
//            System.out.println("Variaveis = " + pdu.getVariableBindings() + " Thread: " + Thread.currentThread().getName());
//
//            //RECEBE AS INFORMAÇÕES DO AGENTE QUE SOLICITA O SISNCRONISMO
//            String descricao = pdu.get(0).getVariable().toString(); // descrição solictação
//            String tipoAtivo = pdu.get(1).getVariable().toString(); //tipo dispositivo
//            String ipAddress = pdu.get(2).getVariable().toString(); // ip
//            String instante = pdu.get(3).getVariable().toString(); // instante requisição
//
//            TrapObject trapObject = new TrapObject(descricao, tipoAtivo, ipAddress, instante);
//
//            try {
//                Thread.sleep(8000);  //aguardar thresdpool terminar de executar
//                System.out.println("entrando no metodo TRAP --------------------------------------------------- thread  = " + Thread.currentThread().getName());
//                syncService.checkAgentSync(trapObject);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//
//        }
//    }
}
