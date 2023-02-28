package com.example.snmpManager.exceptions;

//falha ao processar o corpo da solicitação
public class UnprocesableEntityExecption extends RuntimeException {
    public UnprocesableEntityExecption(String message) {
        super(message);
    }
}
