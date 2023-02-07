package com.example.snmpManager.objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class WindowsObject extends DeviceObjectAbstract implements Serializable {

    private String sistemaOperacional;
    private String arquiteturaSo;
    private String processador;
    private String memoriaRam;
    private String nomeHost;
    private String dominio;
    private String ultimoUsuarioLogado;

    private List<HardDiskObject> discos = new ArrayList<>();

    public void addHardDisk(String arrayDisk) {
        Gson gson = new Gson();
        List<HardDiskObject> listDisk = gson.fromJson(arrayDisk, new TypeToken<List<HardDiskObject>>(){}.getType());

        listDisk.stream().map(i -> this.discos.add(i)).collect(Collectors.toList());
    }

    public void addInterfaces(String arrayInterfaces) {
        Gson gson = new Gson();
        List<InterfaceObject> listInterfaces = gson.fromJson(arrayInterfaces, new TypeToken<List<InterfaceObject>>(){}.getType());

        listInterfaces.stream().map(i -> super.getInterfaces().add(i)).collect(Collectors.toList());
    }
}
