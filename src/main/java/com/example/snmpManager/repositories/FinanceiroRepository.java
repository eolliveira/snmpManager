package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.FinanceiroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinanceiroRepository extends JpaRepository<FinanceiroEntity, Long> {
    List<FinanceiroEntity> findAllByAtivo_Id(Long id);
}
