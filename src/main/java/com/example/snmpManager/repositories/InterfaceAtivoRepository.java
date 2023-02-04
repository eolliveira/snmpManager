package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.InterfaceAtivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterfaceAtivoRepository extends JpaRepository<InterfaceAtivoEntity, Long> {
    List<InterfaceAtivoEntity> findAllByEstacaoTrabalho_Id(Long id);
}
