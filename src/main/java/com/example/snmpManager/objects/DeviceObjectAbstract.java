package com.example.snmpManager.objects;

import com.example.snmpManager.objects.EstacaoTrabalhoObjects.InterfaceObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class DeviceObjectAbstract {
    private String fabricante;
    private String numeroSerie;
    private String modelo;
    private String gateway;
    private String dnsList;

    private List<InterfaceObject> interfaces = new ArrayList<>();
}
