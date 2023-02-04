package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.DiscoAtivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscoAtivoRepository extends JpaRepository<DiscoAtivoEntity, Long> {
    List<DiscoAtivoEntity> findAllByEstacaoTrabalho_Id(Long id);
}
