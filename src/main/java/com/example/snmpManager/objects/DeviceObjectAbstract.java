package com.example.snmpManager.objects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class DeviceObjectAbstract {
    private String manufacturer;
    private String serialNumber;
    private String model;
    private String gateway;
    private String dns;

    private List<InterfaceObject> interfaces = new ArrayList<>();

}
