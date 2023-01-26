package com.example.snmpManager.objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class WindowsObject extends DeviceObjectAbstract implements Serializable {

    private String os;
    private String osArchitecture;
    private String processor;
    private String ramMemory;
    private String hostname;
    private String domain;
    private String usuarioLogado;


    public void addInterfaces(String arrayInterfaces) {
        TypeToken tt = new TypeToken<List<InterfaceObject>>() {};
        Gson gson = new Gson();
        List<InterfaceObject> listInterfaces = gson.fromJson(arrayInterfaces, tt.getType());

        listInterfaces.stream().map(i -> super.getInterfaces().add(i)).collect(Collectors.toList());
    }
}
