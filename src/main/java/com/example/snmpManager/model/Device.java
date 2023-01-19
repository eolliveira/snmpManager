package com.example.snmpManager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICE")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sysObjID;//OID
    private String sysDescr; //Hardware
    private String SysName; //hostname
    private String SysUpTime; //tempo ligado
    private String ipAdEntAddr; //tempo ligado
    private String hrMemorySize; //memoria ram intalada

    public Device() {
    }

    public Device(Long id, String sysObjID, String sysDescr, String sysName, String sysUpTime, String ipAdEntAddr, String hrMemorySize) {
        this.id = id;
        this.sysObjID = sysObjID;
        this.sysDescr = sysDescr;
        SysName = sysName;
        SysUpTime = sysUpTime;
        this.ipAdEntAddr = ipAdEntAddr;
        this.hrMemorySize = hrMemorySize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysObjID() {
        return sysObjID;
    }

    public void setSysObjID(String sysObjID) {
        this.sysObjID = sysObjID;
    }

    public String getSysDescr() {
        return sysDescr;
    }

    public void setSysDescr(String sysDescr) {
        this.sysDescr = sysDescr;
    }

    public String getSysName() {
        return SysName;
    }

    public void setSysName(String sysName) {
        SysName = sysName;
    }

    public String getSysUpTime() {
        return SysUpTime;
    }

    public void setSysUpTime(String sysUpTime) {
        SysUpTime = sysUpTime;
    }

    public String getIpAdEntAddr() {
        return ipAdEntAddr;
    }

    public void setIpAdEntAddr(String ipAdEntAddr) {
        this.ipAdEntAddr = ipAdEntAddr;
    }

    public String getHrMemorySize() {
        return hrMemorySize;
    }

    public void setHrMemorySize(String hrMemorySize) {
        this.hrMemorySize = hrMemorySize;
    }
}
