package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.MovimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentoAtivoRepository extends JpaRepository<MovimentoEntity, Long> {
}
