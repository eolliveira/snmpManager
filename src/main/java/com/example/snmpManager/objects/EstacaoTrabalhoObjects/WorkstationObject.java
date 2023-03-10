package com.example.snmpManager.objects.EstacaoTrabalhoObjects;

import com.example.snmpManager.objects.DeviceObjectAbstract;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class WorkstationObject extends DeviceObjectAbstract implements Serializable {

    private String sistemaOperacional;
    private String arquiteturaSo;
    private String processador;
    private String memoriaRam;
    private String tempoLigado;
    private String nomeHost;
    private String dominio;
    private String ultimoUsuarioLogado;

    private List<HardDiskObject> discos = new ArrayList<>();
    private List<PrinterObject> impressoras = new ArrayList<>();
    private List<VideoCardObject> placasVideo = new ArrayList<>();
    private List<ProgramObject> programas = new ArrayList<>();

    public void addPrinters(String arrayPrinters) {
        try {
            Gson gson = new Gson();
            List<PrinterObject> listImpressora = gson.fromJson(arrayPrinters, new TypeToken<List<PrinterObject>>() {
            }.getType());
            listImpressora.stream().map(printer -> this.impressoras.add(printer)).collect(Collectors.toList());
        } catch (Exception e) {
            //TODO(Tratar)
            System.out.println("Não foi possivel obter lista de impressoras do ativo");
        }

    }

    public void addHardDisk(String arrayDisk) {
        try {
            Gson gson = new Gson();
            List<HardDiskObject> listDisk = gson.fromJson(arrayDisk, new TypeToken<List<HardDiskObject>>() {
            }.getType());
            listDisk.stream().map(disk -> this.discos.add(disk)).collect(Collectors.toList());
        } catch (Exception e) {
            //TODO(Tratar)
            System.out.println("Não foi possivel obter lista de Discos Rigidos do ativo");
        }
    }

    public void addInterfaces(String arrayInterfaces) {
        try {
            Gson gson = new Gson();

            //TODO()Pode lançar exceção ao buscar enquanto agente esta inicializando
            List<InterfaceObject> listInterfaces = gson.fromJson(arrayInterfaces, new TypeToken<List<InterfaceObject>>() {
            }.getType());
            listInterfaces.stream().map(i -> super.getInterfaces().add(i)).collect(Collectors.toList());
        } catch (Exception e) {
            //TODO(Tratar)
            System.out.println("Não foi possivel obter lista de interfaces do ativo");
        }
    }

    public void addVideoCards(String arrayPlacasVideo) {
        try {
            Gson gson = new Gson();
            List<VideoCardObject> listVideoCards = gson.fromJson(arrayPlacasVideo, new TypeToken<List<VideoCardObject>>() {
            }.getType());
            listVideoCards.stream().map(videoCard -> this.placasVideo.add(videoCard)).collect(Collectors.toList());
        } catch (Exception e) {
            //TODO(Tratar)
            System.out.println("Não foi possivel obter lista de placas de video do ativo");
        }
    }

    public void addSoftware(String arrayPrograms) {
        try {
            Gson gson = new Gson();
            List<ProgramObject> listPrograms = gson.fromJson(arrayPrograms, new TypeToken<List<ProgramObject>>() {
            }.getType());
            listPrograms.stream().map(p -> this.programas.add(p)).collect(Collectors.toList());
        } catch (Exception e) {
            //TODO(Tratar)
            System.out.println("Não foi possivel obter lista de programas do ativo");
        }
    }
}
