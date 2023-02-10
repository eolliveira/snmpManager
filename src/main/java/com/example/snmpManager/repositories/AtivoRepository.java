package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.AtivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtivoRepository extends JpaRepository<AtivoEntity, Long> {
}
