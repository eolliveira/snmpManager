package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.AtivoDiscoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtivoDiscoRepository extends JpaRepository<AtivoDiscoEntity, Long> {
    List<AtivoDiscoEntity> findAllByEstacaoTrabalho_Id(Long id);
}
