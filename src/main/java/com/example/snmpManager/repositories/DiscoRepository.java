package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.DiscoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscoRepository extends JpaRepository<DiscoEntity, Long> {
    List<DiscoEntity> findAllByEstacaoTrabalho_Id(Long id);

    void deleteAllByEstacaoTrabalho_Id(Long id);

}
