package com.example.snmpManager.exceptions;

//falha ao conectar-se ao agente
public class ClientConnectionFailedExecption extends RuntimeException {
    public ClientConnectionFailedExecption(String message) {
        super(message);
    }
}
