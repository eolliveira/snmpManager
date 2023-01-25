package com.example.snmpManager.objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class WindowsObject implements Serializable {

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
    private List<InterfaceObject> interfaces = new ArrayList<>();

    public void addInterfaces(String arrayInterfaces) {
        TypeToken tt = new TypeToken<List<InterfaceObject>>() {};
        Gson gson = new Gson();
        List<InterfaceObject> listInterfaces = gson.fromJson(arrayInterfaces, tt.getType());

        listInterfaces.stream().map(i -> this.interfaces.add(i)).collect(Collectors.toList());
    }
}
