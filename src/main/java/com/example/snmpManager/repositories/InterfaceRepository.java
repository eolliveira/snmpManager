package com.example.snmpManager.repositories;

import com.example.snmpManager.entities.InterfaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterfaceRepository extends JpaRepository<InterfaceEntity, Long> {
    List<InterfaceEntity> findAllByEstacaoTrabalho_Id(Long id);
    InterfaceEntity findByEnderecoIp(String ip);
    @Modifying
    @Query("DELETE FROM InterfaceEntity i WHERE i.estacaoTrabalho.id = :estacaoTrabalhoId")
    void deleteAllByEstacaoTrabalhoId(@Param("estacaoTrabalhoId") Long estacaoTrabalhoId);
}
