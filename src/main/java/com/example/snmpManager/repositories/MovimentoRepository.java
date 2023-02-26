package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.InterfaceEntity;
import com.example.snmpManager.entities.MovimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentoRepository extends JpaRepository<MovimentoEntity, Long> {
    List<MovimentoEntity> findAllByAtivo_Id(Long id);
}
