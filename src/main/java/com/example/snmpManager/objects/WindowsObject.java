package com.example.snmpManager.objects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
    private String gateway;
    private String dns;
    private String intefaces;

}
