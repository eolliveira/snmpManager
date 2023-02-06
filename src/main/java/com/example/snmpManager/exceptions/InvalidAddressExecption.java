package com.example.snmpManager.exceptions;

//endereço invalido
public class InvalidAddressExecption extends RuntimeException {
    public InvalidAddressExecption(String message) {
        super(message);
    }
}
