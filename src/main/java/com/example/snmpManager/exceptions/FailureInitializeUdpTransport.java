package com.example.snmpManager.exceptions;

public class FailureInitializeUdpTransport extends RuntimeException {
    public FailureInitializeUdpTransport(String message) {
        super(message);
    }
}
