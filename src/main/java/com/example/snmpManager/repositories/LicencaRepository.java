package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.LicencaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LicencaRepository extends JpaRepository<LicencaEntity, Long> {
    @Query("SELECT licenca FROM LicencaEntity licenca JOIN licenca.ativos ativo WHERE ativo.id = :id")
    List<LicencaEntity> findAllByAtivoId(Long id);
}
