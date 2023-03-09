package com.example.snmpManager.services.SnmpService;

import com.example.snmpManager.objects.EstacaoTrabalhoObjects.WorkstationObject;
import com.example.snmpManager.services.EstacaoTrabalhoService.SyncWorkstationByIpAddressService;
import lombok.RequiredArgsConstructor;
import org.snmp4j.*;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.TransportIpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SNMPTrapReciever implements CommandResponder {
    private final SyncWorkstationByIpAddressService syncWorkstationByIpAddressService;

    public void listen(TransportIpAddress address) throws IOException, InterruptedException {
        // Create a UDP transport mapping
        TransportMapping transport = new DefaultUdpTransportMapping(new UdpAddress(address + "/162"));

        // Create a SNMP object
        Snmp snmp = new Snmp(transport);

        // Start the transport
        transport.listen();

        // Add the trap receiver
        snmp.addCommandResponder(this);

        System.out.println("Ouvindo armadilhas na porta 162...");

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
            System.out.println("Armadilha recebida de " + address + " - Thread: " + Thread.currentThread().getName());

            // Get the community string used by the trap sender
            String community = new String(event.getSecurityName());
            //TODO(configurar nome da communidade)

            // Get the variable bindings from the trap
            VariableBinding[] varBinds = pdu.toArray();

            for (VariableBinding varBind : varBinds) {
                System.out.println("Variable: " + varBind.getOid() + " = " + varBind.getVariable());
            }

            //RECEBE AS INFORMAÇÕES DO AGENTE QUE SOLICITA O SISNCRONISMO
            String descricao = pdu.get(0).getVariable().toString();
            String tipoAtivo = pdu.get(1).getVariable().toString();
            String ipAddress = pdu.get(2).getVariable().toString();
            String instante = pdu.get(3).getVariable().toString();

            if (Objects.equals(tipoAtivo, "WORKSTATION")) {

                String so = pdu.get(4).getVariable().toString();
                String arquiteturaSo = pdu.get(5).getVariable().toString();
                String fabricante = pdu.get(6).getVariable().toString();
                String modelo = pdu.get(7).getVariable().toString();
                String numeroSerie = pdu.get(8).getVariable().toString();
                String processador = pdu.get(9).getVariable().toString();
                String memoriaRam = pdu.get(10).getVariable().toString();
                String hostName = pdu.get(11).getVariable().toString();
                String dominio = pdu.get(12).getVariable().toString();
                String ultmUsuario = pdu.get(13).getVariable().toString();
                String tempoLigado = pdu.get(14).getVariable().toString();
                String gatway = pdu.get(15).getVariable().toString();
                String dns = pdu.get(16).getVariable().toString();
                String interfaces = pdu.get(17).getVariable().toString();
                String discos = pdu.get(18).getVariable().toString();
                String impressoras = pdu.get(19).getVariable().toString();
                String placasVideo = pdu.get(20).getVariable().toString();
                String programas = pdu.get(21).getVariable().toString();

                WorkstationObject workstationObject = new WorkstationObject();
                workstationObject.setSistemaOperacional(so);
                workstationObject.setArquiteturaSo(arquiteturaSo);
                workstationObject.setFabricante(fabricante);
                workstationObject.setModelo(modelo);
                workstationObject.setNumeroSerie(numeroSerie);
                workstationObject.setProcessador(processador);
                workstationObject.setDominio(dominio);
                workstationObject.setMemoriaRam(memoriaRam);
                workstationObject.setNomeHost(hostName);
                workstationObject.setUltimoUsuarioLogado(ultmUsuario);
                workstationObject.setTempoLigado(tempoLigado);
                workstationObject.setGateway(gatway);
                workstationObject.setDnsList(dns);
                workstationObject.addInterfaces(interfaces);
                workstationObject.addHardDisk(discos);
                //workstationObject.addImpressoras(interfaces);
                workstationObject.addVideoCards(placasVideo);
                workstationObject.addSoftware(programas);

                syncWorkstationByIpAddressService.synchronizeWorstation(ipAddress, workstationObject);
            }


        }
    }


}
