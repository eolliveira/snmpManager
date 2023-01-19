package com.example.snmpManager.model;

import java.util.Vector;

import org.snmp4j.smi.VariableBinding;

public interface SNMPResponseListener {
    public void onSNMPResponseReceived(Vector<? extends VariableBinding> variableBinding);
}
