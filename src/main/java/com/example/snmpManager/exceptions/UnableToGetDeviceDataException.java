package com.example.snmpManager.exceptions;

//falha ao obter dados do agente
public class UnableToGetDeviceDataException extends RuntimeException{
    public UnableToGetDeviceDataException(String message) {
        super(message);
    }
}
