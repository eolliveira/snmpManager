package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.InterfaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterfaceRepository extends JpaRepository<InterfaceEntity, Long> {
    List<InterfaceEntity> findAllByEstacaoTrabalho_Id(Long id);

    InterfaceEntity findByEnderecoIp(String ip);
}
