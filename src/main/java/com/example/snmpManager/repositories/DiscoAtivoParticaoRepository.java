package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.AtivoDiscoParticaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscoAtivoParticaoRepository extends JpaRepository<AtivoDiscoParticaoEntity, Long> {
}
