package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.CustoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustoRepository extends JpaRepository<CustoEntity, Long> {
    List<CustoEntity> findAllByAtivo_Id(Long id);
}
