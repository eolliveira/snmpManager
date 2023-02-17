package com.example.snmpManager.objects.EstacaoTrabalhoObjects.WindowsObjects;

import com.example.snmpManager.objects.DeviceObjectAbstract;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class WindowsObject extends DeviceObjectAbstract implements Serializable {

    private String sistemaOperacional;
    private String arquiteturaSo;
    private String processador;
    private String memoriaRam;
    private String nomeHost;
    private String dominio;
    private String ultimoUsuarioLogado;

    private List<HardDiskObject> discos = new ArrayList<>();
    private List<PrinterObject> impressoras = new ArrayList<>();
    private List<VideoCardObject> placasVideo = new ArrayList<>();
    private List<ProgramObject> programas = new ArrayList<>();

    public void addPrinters(String arrayPrinters) {
        Gson gson = new Gson();
        List<PrinterObject> listImpressora = gson.fromJson(arrayPrinters, new TypeToken<List<PrinterObject>>(){}.getType());
        listImpressora.stream().map(impressora -> this.impressoras.add(impressora)).collect(Collectors.toList());
    }

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

    public void addVideoCards(String arrayPlacasVideo) {
        Gson gson = new Gson();
        List<VideoCardObject> listVideoCards = gson.fromJson(arrayPlacasVideo, new TypeToken<List<VideoCardObject>>(){}.getType());
        listVideoCards.stream().map(p -> this.placasVideo.add(p)).collect(Collectors.toList());
    }

    public void addSoftware(String arrayPrograms) {
        Gson gson = new Gson();
        List<ProgramObject> listPrograms = gson.fromJson(arrayPrograms, new TypeToken<List<ProgramObject>>(){}.getType());
        listPrograms.stream().map(p -> this.programas.add(p)).collect(Collectors.toList());
    }
}
