package com.example.snmpManager.objects;

import lombok.Data;

@Data
public class WindowsObject {

    private String os;
    private String architectureSo;
    private String manufacturer;
    private String model;
    private String serialNumber;
    private String processor;
    private String ramMemory;
    private String hostname;
    private String domain;
    private String ipAdrress;
    private String macAddress;
    private String gateway;
    private String primaryDns;
    private String secondaryDns;

}
