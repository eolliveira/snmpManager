package com.example.snmpManager.services;

import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import com.example.snmpManager.entities.ManagedDevice;
import com.example.snmpManager.entities.SNMPResponseListener;

public class SNMPMessenger {
    private static final String community = "public";
    private static final int snmpVersion = SnmpConstants.version2c;
    
    private String ip;
    private String port;
    private CommunityTarget comtarget;

    public SNMPMessenger(String ip, String port) {
        this.ip = ip;
        this.port = port;
        
        // Create Target Address object
        comtarget = new CommunityTarget();
        comtarget.setCommunity(new OctetString(community));
        comtarget.setVersion(snmpVersion);
        comtarget.setAddress(new UdpAddress(ip + "/" + port));
        comtarget.setRetries(2);
        comtarget.setTimeout(1000);
    }
    
//    public void getTable(OID[] columns, final SNMPTableResponseListener responseListener) {
//        try {
//            // Create Snmp object for sending data to Agent
//            Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
//            snmp.listen();
//
//            TableUtils tableUtils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));
//            
//            TableListener listener = new TableListener() {
//                private boolean finished = false;
//
//                public boolean next(TableEvent event) {
//                    System.out.println("OID: " + event.getIndex());
//                    System.out.println("Columns:");
//                    
//                    int numColumns = event.getColumns().length;
//                    
//                    String[] row = new String[numColumns];
//                    
//                    for(int i = 0; i < numColumns; i++) {
//                        System.out.println(i + ": " + event.getColumns()[i]);
//                        row[i] = event.getColumns()[i].toValueString();
//                        System.out.println("Row: " + row[i]);
//                    }
//                    
//                    responseListener.onRowReceived(row);
//                    
//                    return true;
//                }
//
//                public void finished(TableEvent event) {
//                    finished = true;
//                }
//
//                public boolean isFinished() {
//                    return finished;
//                }
//            };
//            
//            System.out.println("Starting to get table ...");
//            
//            tableUtils.getTable(comtarget, columns, listener, null, null, null);
//            
//        } catch (java.io.IOException e) {
//            e.printStackTrace();
//        }
//    }
    														//ouvinte resposta
    public void sendGetRequest(OID oid, final SNMPResponseListener responseListener) {
        try {
            // Create the PDU object
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid)));
            pdu.setType(PDU.GET);

            // Create Snmp object for sending data to Agent
            Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
            snmp.listen();

            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    // Always cancel async request when response has been received
                    // otherwise a memory leak is created! Not canceling a request
                    // immediately can be useful when sending a request to a broadcast
                    // address.
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);
                    
                    PDU response = event.getResponse();
                    PDU request = event.getRequest();
                    
                    //se obter resposta , retorna o valor 
                    if (response != null) {
                        System.out.println("Obter resposta do agente: " + response.toString());
                        int errorStatus = response.getErrorStatus();
                        int errorIndex = response.getErrorIndex();
                        String errorStatusText = response.getErrorStatusText();

                        //peda valores da resposta 
                        if (errorStatus == PDU.noError) {
                            System.out.println("SNMP obter resposta = " + response.getVariableBindings());
                            
                            ///passsa para response listener 
                            responseListener.onSNMPResponseReceived(response.getVariableBindings());
                        } else {
                            System.out.println("Error: Request Failed");
                            System.out.println("Error Status = " + errorStatus);
                            System.out.println("Error Index = " + errorIndex);
                            System.out.println("Error Status Text = " + errorStatusText);
                        }
                    } else {
                
                    	//se resposta for nula
                        System.out.println("Error: Agent Timeout... ");
                    }
                }
            };
            
            System.out.println("Sending Request to Agent...");
            
            snmp.send(pdu, comtarget, "user_handle_object", listener);
            
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    
    public void sendSetRequest(OID oid, String message, final SNMPResponseListener responseListener) {
        try {
            // Create the PDU object
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid), new OctetString(message)));
            pdu.setType(PDU.SET);

            // Create Snmp object for sending data to Agent
            Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
            snmp.listen();

            
            
            
            
            
            
            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    // Always cancel async request when response has been received
                    // otherwise a memory leak is created! Not canceling a request
                    // immediately can be useful when sending a request to a broadcast
                    // address.
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);
                    PDU response = event.getResponse();
                    PDU request = event.getRequest();
                    if (response != null) {
                        System.out.println("Got Response from Agent: " + response.toString());
                        int errorStatus = response.getErrorStatus();
                        int errorIndex = response.getErrorIndex();
                        String errorStatusText = response.getErrorStatusText();

                        if (errorStatus == PDU.noError) {
                            System.out.println("Snmp Set Response = " + response.getVariableBindings());
                            responseListener.onSNMPResponseReceived(response.getVariableBindings());
                        } else {
                            System.out.println("Error: Request Failed");
                            System.out.println("Error Status = " + errorStatus);
                            System.out.println("Error Index = " + errorIndex);
                            System.out.println("Error Status Text = " + errorStatusText);
                        }
                    } else {
                        System.out.println("Error: Agent Timeout... ");
                    }
                }
            };
            
            
            System.out.println("Sending Request to Agent...");
            
            snmp.send(pdu, comtarget, "user_handle_object", listener);
            
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void setIP(String ip) {
        this.ip = ip;
        comtarget.setAddress(new UdpAddress(ip + "/" + port));
    }
    				  //descobrir dispositivos	 
    public static void discoverDevices(int[] begin, int[] end /*final ManagerFrame frame*/) {
    	
    	     //ip atual
        int[] current = begin.clone();
                                                                   //monta ip de inicio informando a porta 
        SNMPMessenger messenger = new SNMPMessenger(current[0] + "." + current[1] + "." + current[2] + "." + current[3], ManagedDevice.port);
        
        //percorre faixa de ip
        for(int i = begin[3]; i <= end[3]; i++) {
            current[3] = i;
            final String currentIP = current[0] + "." + current[1] + "." + current[2] + "." + current[3];
            
            
            //passa ip atual para as configura��es do target
            messenger.setIP(current[0] + "." + current[1] + "." + current[2] + "." + current[3]);
            
            
            
            messenger.sendGetRequest(new OID(new int[] {1,3,6,1,4,1,12619,1,1,1,0}), new SNMPResponseListener() {
            	
                //manda resultados de ips encontrados para a lista?
            	public void onSNMPResponseReceived(Vector<? extends VariableBinding> variableBinding) {
                    //frame.addDevice(currentIP).setModelName(variableBinding.get(0).toValueString());
            		System.out.println("ip encontrado: " + currentIP + " - " + variableBinding.get(0).toValueString());
                }
                
            });
        }
    }
}