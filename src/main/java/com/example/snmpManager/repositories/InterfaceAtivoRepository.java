package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.AtivoInterfaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterfaceAtivoRepository extends JpaRepository<AtivoInterfaceEntity, Long> {
    List<AtivoInterfaceEntity> findAllByEstacaoTrabalho_Id(Long id);
}
