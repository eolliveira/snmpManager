package com.example.snmpManager.objects;

import com.google.gson.Gson;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WindowsObject {

    private String os;
    private String osArchitecture;
    private String manufacturer;
    private String serialNumber;
    private String model;
    private String processor;
    private String ramMemory;
    private String hostname;
    private String domain;
    private String gateway;
    private String dns;
    private List<InterfaceObject> intefaces = new ArrayList<>();

    public void addInterfaces(String arrayInterfaces) {

//        Gson gson = new Gson();
//        InterfaceObject inter = gson. fromJson(intefaces, InterfaceObject.class);

        //TODO(converter string em obj )



        this.os = os;
        this.osArchitecture = osArchitecture;
        this.manufacturer = manufacturer;
        this.serialNumber = serialNumber;
        this.model = model;
        this.processor = processor;
        this.ramMemory = ramMemory;
        this.hostname = hostname;
        this.domain = domain;
        this.gateway = gateway;
        this.dns = dns;
    }
}
