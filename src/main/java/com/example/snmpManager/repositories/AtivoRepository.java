package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.AtivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AtivoRepository extends JpaRepository<AtivoEntity, Long> {
    @Query(value = "SELECT COUNT(*) FROM ATIVOLICENCA A WHERE A.ID_LICENCA = :idAtivo", nativeQuery = true)
    long countCurrentLicensesPerAsset(Long idAtivo);
}
