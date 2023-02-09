package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.InterfaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtivoInterfaceRepository extends JpaRepository<InterfaceEntity, Long> {
    List<InterfaceEntity> findAllByEstacaoTrabalho_Id(Long id);
}
