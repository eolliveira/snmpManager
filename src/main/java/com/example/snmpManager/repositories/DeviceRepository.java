package com.example.snmpManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.snmpManager.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}
